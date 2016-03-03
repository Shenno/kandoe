import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Organisation} from '../entity/organisation';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";


@Component({
    selector: 'detail-organisation',
    templateUrl: 'app/partials_html/detailOrganisation.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class DetailOrganisationComponent {

    private router: Router;

    private userService: UserService;

    private organisation: Organisation = Organisation.createEmptyOrganisation();

    public constructor(userService: UserService, routeParam:RouteParams, router:Router) {
        this.router = router;
        this.userService = userService;
        userService.getOrganisation(routeParam.params["organisationId"]).subscribe((organisation:Organisation) => {
            this.organisation = organisation;
            document.title = 'Organisatie: ' + this.organisation.name;
        })
    }
    public backToList() : void {
        this.router.navigate(['/Organisations']);
    }
}