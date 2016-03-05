import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {ContentService} from "../service/contentService";
import {Theme} from "../entity/theme";


@Component({
    selector: 'overview-theme',
    templateUrl: 'app/partials_html/overviewThemes.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})

export class OverviewThemeComponent {
    private router:Router;
    private contentService:ContentService;
    private themes:Theme[] = [];

    public constructor(contentService:ContentService, routeParam:RouteParams, router:Router) {
        this.router = router;
        this.contentService = contentService;
        contentService.getThemesByOrganisatorId(routeParam.get("organisationId")).subscribe((themes:Theme[]) => {
            this.themes = themes;
            document.title = 'Themas';
        })
    }
    public addTheme(): void {
        this.router.navigate(['/CreateTheme']);
    }
    public detailTheme(id:string):void{
        this.router.navigate(['/DetailTheme',{themeId:id}]);
    }
}