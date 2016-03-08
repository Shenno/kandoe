import {Component, ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Tag} from '../entity/tag';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {ContentService} from "../service/contentService";
import {Theme} from "../entity/theme";

@Component({
    selector: 'edit-theme',
    templateUrl: 'app/partials_html/editTheme.component.html',
    encapsulation: ViewEncapsulation.None
})
export class EditThemeComponent {

    private router: Router;

    private contentService: ContentService;

    //private tag: Tag = Tag.createEmptyTag();
    private theme: Theme = Theme.createEmptyTheme();
    private newTag: string = "";

    public constructor(contentService: ContentService, router:Router, routeParam:RouteParams) {
        this.router = router;
        this.contentService = contentService;
        this.contentService.getTheme(routeParam.params["themeId"]).subscribe((theme:Theme) => {
            this.theme = theme;
            document.title = 'Wijzig thema';
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
        this.contentService.updateTheme(this.theme);
    }

    public onCancel(event): void {
        event.preventDefault();
        this.router.navigate(['/Theme',{themeId: this.theme.themeId}]);
    }
}