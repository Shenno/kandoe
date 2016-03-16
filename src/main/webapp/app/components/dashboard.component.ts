import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Http, Response, Headers} from "angular2/http";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {AprioriComponent} from "./apriori.component";
import {Theme} from "../entity/theme";
import {ContentService} from "../service/contentService";

@Component({
    selector: 'dashboard',
    templateUrl: 'app/partials_html/dashboard.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, AprioriComponent]
})

export class DashboardComponent {

    private router: Router;

    private contentService: ContentService;

    private theme: Theme = Theme.createEmptyTheme();

    public constructor(contentService: ContentService, router: Router, routeParam: RouteParams) {
        this.router = router;
        this.contentService = contentService;
        this.contentService.getTheme(routeParam.params["themeId"]).subscribe((theme:Theme) => {
            this.theme = theme;
            document.title = 'Thema: ' + this.theme.themeName + ' - Dashboard';
        });
    }

    public returnToTheme(event): void {
        event.preventDefault();
        this.router.navigate(['/Theme', {themeId: this.theme.themeId}]);
    }
}