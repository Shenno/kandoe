import {Component, ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Theme} from '../entity/theme';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {ContentService} from "../service/contentService";
import {Organisation} from "../entity/organisation";
import {User} from "../entity/user";
import {UserService} from "../service/userService";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
/**
 * Component for creating Theme
 */
@Component({
    selector: 'create-theme',
    templateUrl: 'app/partials_html/createTheme.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class CreateThemeComponent {

    private router:Router;

    private contentService:ContentService;
    private userService:UserService;

    private theme:Theme = Theme.createEmptyTheme();
    private newTag:string = "";

    private users:string[] = [];
    private newOrganisator:string = "";

    private tagErrorMessage:string = "";
    private organisatorErrorMessage:string = "";

    private currentUser:User = User.createEmptyUser();

    public constructor(contentService:ContentService, userService:UserService, router:Router, routeParam:RouteParams) {
        document.title = 'Maak thema aan';
        this.router = router;
        this.contentService = contentService;
        this.userService = userService;

        this.theme.organisationId = +routeParam.params["organisationId"];
        this.userService.getMyDetails().subscribe((user:User) => {
            this.currentUser = user;
            this.theme.organisatorId = user.id;

            this.userService.getAllUsernames().subscribe((users:string[]) => {
                var index = users.indexOf(this.currentUser.username);
                users.splice(index, 1); //you can't add yourself as organisator
                this.users = users;
            });
        });
    }

    public onEditThemeName(): void {
        this.theme.errorMessage = "";
    }

    public onAddTag():void {
        if (this.newTag != '') {
            var tags = this.newTag.split(" ");
            for (var i in tags) {
                var tag = tags[i].toLowerCase();
                if (this.theme.tags.indexOf(tag) == -1) {
                    this.theme.tags[this.theme.tags.length] = tag;
                    this.tagErrorMessage = '';
                } else {
                    this.tagErrorMessage = 'Tag "' + tag + '" bestaat al';
                }
            }
            this.newTag = "";
        } else {
            this.tagErrorMessage = 'Tag mag niet leeg zijn';
        }
    }

    public onRemoveTag(i:number):void {
        this.theme.tags.splice(i, 1);
    }

    public onAddOrganisator():void {
        if (this.newOrganisator != '') {
            this.theme.organisatorNames.push(this.newOrganisator);
            var index = this.users.indexOf(this.newOrganisator);
            this.users.splice(index, 1);
            this.organisatorErrorMessage = '';
            this.newOrganisator = "";
        } else {
            this.organisatorErrorMessage = 'Gekozen organisator mag niet leeg zijn';
        }
    }

    public onRemoveOrganisator(i:number):void {
        this.users.push(this.theme.organisatorNames[i]);
        this.theme.organisatorNames.splice(i, 1);
    }

    public onSubmit():void {
        this.contentService.addTheme(this.theme);
    }

    public onCancel(event):void {
        event.preventDefault();
        this.router.navigate(['/Organisation', {organisationId: this.theme.organisationId}]);
    }
}