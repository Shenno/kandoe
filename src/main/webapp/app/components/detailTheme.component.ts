import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Theme} from '../entity/theme';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {ContentService} from "../service/contentService";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {Card} from "../entity/card";
import {User} from "../entity/user";
import {UserService} from "../service/userService";


@Component({
    selector: 'detail-theme',
    templateUrl: 'app/partials_html/detailTheme.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class DetailThemeComponent {

    private router:Router;

    private contentService:ContentService;
    private userService: UserService;
    private currentUsername:string = ""
    private theme:Theme = Theme.createEmptyTheme();
    private cards:Card[] = [];

    public constructor(contentService:ContentService,userService:UserService, routeParam:RouteParams, router:Router) {
        this.router = router;
        this.contentService = contentService;
        this.userService = userService;
        contentService.getTheme(routeParam.params["themeId"]).subscribe((theme:Theme) => {
            this.theme = theme;
            document.title = 'Thema: ' + this.theme.themeName;
        });
        contentService.getCardsByThemeId(routeParam.params["themeId"]).subscribe((cards:Card[]) => {
            this.cards = cards;
        });
        this.userService.getMyDetails().subscribe((user:User) => {
            this.currentUsername = user.username;
        });
    }

    public onSubmit(event):void {
        event.preventDefault(); //prevents weird question mark url bug
        this.router.navigate(['/EditTheme', {themeId: this.theme.themeId}]);
    }

    public detailCard(id:string):void {
        this.router.navigate(['/DetailCard', {cardId: id}]);
    }

    public addCard(event) : void{
        event.preventDefault();
        this.router.navigate(['/CreateCard',{themeId:this.theme.themeId}]);
    }

    public navigateToDashboard(event): void {
        event.preventDefault();
        this.router.navigate(['/Dashboard', {themeId: this.theme.themeId}]);
    }

    public backToList(event):void {
        event.preventDefault();
        this.router.navigate(['/Organisation',{organisationId: this.theme.organisationId}]);
    }
}
