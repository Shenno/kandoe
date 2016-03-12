import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {AprioriComponent} from "./apriori.component";

@Component({
    selector: 'dashboard',
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, AprioriComponent],
    templateUrl: 'app/partials_html/dashboard.component.html',
    encapsulation: ViewEncapsulation.None,
})

export class DashboardComponent {
    
}