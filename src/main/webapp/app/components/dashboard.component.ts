import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {AprioriComponent} from "./apriori.component";
import {RouteParams} from "angular2/router";

@Component({
    selector: 'dashboard',
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, AprioriComponent],
    templateUrl: 'app/partials_html/dashboard.component.html',
    encapsulation: ViewEncapsulation.None,
})

export class DashboardComponent {

    private router: Router;
    private routeParam: RouteParams;

    public constructor(router: Router, routeParam: RouteParams) {
        this.router = router;
        this.routeParam = routeParam;
    }

    public returnToTheme(event): void {
        event.preventDefault();
        this.router.navigate(['/Theme', {themeId: this.routeParam.params["themeId"]}]);
    }
}