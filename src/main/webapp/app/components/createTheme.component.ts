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

@Component({
    selector: 'create-theme',
    templateUrl: 'app/partials_html/createTheme.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class CreateThemeComponent {

    private router: Router;

    private contentService: ContentService;
    private userService: UserService;

    private theme: Theme = Theme.createEmptyTheme();
    private newTag: string = "";


    public constructor(contentService: ContentService, userService: UserService, router:Router, routeParam: RouteParams) {
        document.title = 'Maak thema aan';
        this.router = router;
        this.contentService = contentService;
        this.theme.organisationId = +routeParam.params["organisationId"];
        this.userService = userService;
        this.userService.getMyDetails().subscribe((user:User) => {
            this.theme.organisatorId = user.id;
        });
    }

    public onAddTag(): void {
        this.theme.tags[this.theme.tags.length] = this.newTag.toLowerCase();
        this.newTag = "";
    }

    public onRemoveTag(i: number): void {
        this.theme.tags.splice(i, 1);
    }

    public onSubmit(): void {
        this.contentService.addTheme(this.theme);
    }

    public onCancel(event): void {
        event.preventDefault();
        this.router.navigate(['/DetailOrganisation',{organisationId: this.theme.organisationId}]);
    }
}