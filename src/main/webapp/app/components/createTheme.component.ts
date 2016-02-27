import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Theme} from '../entity/theme';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {ContentService} from "../service/contentService";

@Component({
    selector: 'create-theme',
    templateUrl: 'app/partials_html/createTheme.component.html',
    encapsulation: ViewEncapsulation.None
})
export class CreateThemeComponent {

    private contentService: ContentService;

    private theme: Theme = Theme.createEmptyTheme();
        //new Promise<Theme[]>(resolve => setTimeout(() =>resolve(Theme), 2000));

    public constructor(contentService: ContentService) {
        this.contentService = contentService;
    }

    public onSubmit(): void {
        this.contentService.addTheme(this.theme);
    }
}