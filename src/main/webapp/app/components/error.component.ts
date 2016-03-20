import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {Location} from "angular2/router";

/**
 * Component for Error Page
 */
@Component({
    selector: 'error',
    templateUrl: 'app/partials_html/error.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})

export class ErrorComponent {

    private location: Location;

    constructor(location: Location) {
        document.title = 'Kand-OEPS';
        this.location = location;
    }

    public onGoBack(event): void {
        event.preventDefault();
        this.location.back();
    }
}