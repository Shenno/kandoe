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

    private currentUser: User = User.createEmptyUser();

    private tagErrorMessage: string = "";
    private organisatorErrorMessage:string = "";

    public constructor(contentService: ContentService, userService: UserService, router:Router, routeParam:RouteParams) {
        this.router = router;
        this.contentService = contentService;
        this.userService = userService;
        this.contentService.getTheme(routeParam.params["themeId"]).subscribe((theme:Theme) => {
            this.theme = theme;
            document.title = 'Wijzig thema';
        });
        this.userService.getMyDetails().subscribe((user:User) => {
            this.currentUser = user;
            this.userService.getAllUsernames().subscribe((users:string[]) => {
                var index = users.indexOf(this.currentUser.username);
                users.splice(index, 1); //you can't add yourself as organisator
                this.users = users;
                index = this.theme.organisatorNames.indexOf(this.currentUser.username);
                this.theme.organisatorNames.splice(index,1);
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


    public onSubmit(): void {
        this.contentService.updateTheme(this.theme);
    }

    public onCancel(event): void {
        event.preventDefault();
        this.router.navigate(['/Theme',{themeId: this.theme.themeId}]);
    }
}