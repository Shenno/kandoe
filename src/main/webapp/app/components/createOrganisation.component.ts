import {Component, ViewEncapsulation} from 'angular2/core';
import {Router} from 'angular2/router';
import {Organisation} from '../entity/organisation';
import {User} from "../entity/user";
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";

@Component({
    selector: 'create-organisation',
    templateUrl: 'app/partials_html/createOrganisation.component.html',
    encapsulation: ViewEncapsulation.None
})
export class CreateOrganisationComponent {
    private router: Router
    private userService: UserService;

    private organisation: Organisation = Organisation.createEmptyOrganisation();

    public constructor(userService: UserService, router: Router) {
        this.router=router;
        this.userService = userService;
        this.userService.getMyDetails().subscribe((user:User) => {
            this.organisation.organisatorId = user.id;
        });
        document.title = 'Maak organisatie aan';
    }

    public onSubmit(): void {
        this.userService.addOrganisation(this.organisation);
    }
    public onCancel(event): void {
        event.preventDefault();
        this.router.navigate(['/Organisations']);
    }
}