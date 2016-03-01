import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Organisation} from '../entity/organisation';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {User} from "../entity/user";

@Component({
    selector: 'create-organisation',
    templateUrl: 'app/partials_html/createOrganisation.component.html',
    encapsulation: ViewEncapsulation.None
})
export class CreateOrganisationComponent {

    private userService: UserService;

    private organisation: Organisation = Organisation.createEmptyOrganisation();

    public constructor(userService: UserService) {
        this.userService = userService;
        document.title = 'Maak organisatie aan';
    }

    public onSubmit(): void {
        this.organisation.organisator = new User(1, "test");
        this.userService.addOrganisation(this.organisation);
    }
}