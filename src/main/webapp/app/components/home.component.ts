import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";

/** Component for Home page
@Component({
selector: 'home',
templateUrl: 'app/partials_html/home.component.html',
encapsulation: ViewEncapsulation.None,
directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})

export class HomeComponent {
    public constructor() {
        document.title = 'Kandoe';
    }
}