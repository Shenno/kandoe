import {Component, ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Tag} from '../entity/tag';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {ContentService} from "../service/contentService";
import {Theme} from "../entity/theme";
import {UserService} from "../service/userService";
import {User} from "../entity/user";

@Component({
    selector: 'edit-theme',
    templateUrl: 'app/partials_html/editTheme.component.html',
    encapsulation: ViewEncapsulation.None
})
export class EditThemeComponent {

    private router: Router;

    private contentService: ContentService;
    private userService: UserService;

    //private tag: Tag = Tag.createEmptyTag();
    private theme: Theme = Theme.createEmptyTheme();
    private newTag: string = "";

    private users: string[] = [];
    private newOrganisator: string = "";

    private currentUsername: string = "";

    private tagErrorMessage: string = "";

    public constructor(contentService: ContentService, userService: UserService, router:Router, routeParam:RouteParams) {
        this.router = router;
        this.contentService = contentService;
        this.userService = userService;
        this.contentService.getTheme(routeParam.params["themeId"]).subscribe((theme:Theme) => {
            this.theme = theme;
            document.title = 'Wijzig thema';
        });
        this.userService.getMyDetails().subscribe((user:User) => {
            this.currentUsername = user.username;
            this.userService.getAllUsernames().subscribe((users:string[]) => {
                this.users = users;
                var i = users.indexOf(this.currentUsername);
                this.users.splice(i,1);
                i = this.theme.organisatorNames.indexOf(this.currentUsername);
                this.theme.organisatorNames.splice(i,1);
            });
        });
    }

    public onAddTag(): void {
        if (this.newTag != '') {
            var tags = this.newTag.split(" ");
            for (var i in tags) {
                var tag = tags[i].toLowerCase();
                if (this.theme.tags.indexOf(tag) == -1) {
                    this.theme.tags[this.theme.tags.length] = tag;
                    this.tagErrorMessage = '';
                } else {
                    this.tagErrorMessage = 'Tag "' + tag + '" already exists';
                }
            }
            this.newTag = "";
        } else {
            this.tagErrorMessage = 'Tag cannot be empty';
        }
    }

    public onRemoveTag(i: number): void {
        this.theme.tags.splice(i, 1);
    }

    public onAddOrganisator(): void {
        this.theme.organisatorNames.push(this.newOrganisator);
    }

    public onRemoveOrganisator(i: number): void {
        this.theme.organisatorNames.splice(i, 1);
    }

    public onSubmit(): void {
        this.contentService.updateTheme(this.theme);
    }

    public onCancel(event): void {
        event.preventDefault();
        this.router.navigate(['/Theme',{themeId: this.theme.themeId}]);
    }
}