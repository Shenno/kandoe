import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Tag} from '../entity/tag';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {ContentService} from "../service/contentService";
import {RouteParams} from "angular2/router";

@Component({
    selector: 'edit-theme',
    templateUrl: 'app/partials_html/editTheme.component.html',
    encapsulation: ViewEncapsulation.None
})
export class EditThemeComponent {
    private contentService: ContentService;

    private tag: Tag = Tag.createEmptyTag();

    public constructor(contentService: ContentService,routeParam:RouteParams,router:Router) {
        //this.router = router;

        this.contentService = contentService;
    }

    public onSubmit(): void {
        this.contentService.addTag(this.tag);
    }
}