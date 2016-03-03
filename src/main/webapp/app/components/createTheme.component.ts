import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Theme} from '../entity/theme';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {ContentService} from "../service/contentService";
import {Organisation} from "../entity/organisation";
import {User} from "../entity/user";
import {RouteParams} from "angular2/router";
import {UserService} from "../service/userService";

@Component({
    selector: 'create-theme',
    templateUrl: 'app/partials_html/createTheme.component.html',
    encapsulation: ViewEncapsulation.None
})
export class CreateThemeComponent {

    private contentService: ContentService;
    private userService: UserService;

    private theme: Theme = Theme.createEmptyTheme();
        //new Promise<Theme[]>(resolve => setTimeout(() =>resolve(Theme), 2000));

    public constructor(contentService: ContentService, userService: UserService, routeParam: RouteParams) {
        this.contentService = contentService;
        document.title = 'Maak thema aan';
        this.theme.organisationId = +routeParam.params["organisationId"];
        this.userService = userService;
        this.userService.getMyDetails().subscribe((user:User) => {
            this.theme.organisatorId = user.id;
        });
    }

    public onSubmit(): void {
        this.contentService.addTheme(this.theme);
    }
}