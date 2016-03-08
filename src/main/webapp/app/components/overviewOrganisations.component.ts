import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Organisation} from '../entity/organisation';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {User} from "../entity/user";


@Component({
    selector: 'overview-organisation',
    templateUrl: 'app/partials_html/overviewOrganisations.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class OverviewOrganisationComponent {

    private router:Router;

    private userService:UserService;
    private userId =0;
    private organisations:Organisation[] = [];

    public constructor(userService:UserService, routeParam:RouteParams, router:Router) {
        this.router = router;
        this.userService = userService;
        userService.getOrganisations().subscribe((organisations:Organisation[]) => {
            this.organisations = organisations;
            document.title = 'Organisaties';
        })
        this.userService.getMyDetails().subscribe((user:User) => {
            this.userId = user.id;
        });
    }
    public addOrganisation(): void {
        this.router.navigate(['/CreateOrganisation']);
    }
    public detailOrganisation(id:string):void{
        this.router.navigate(['/Organisation',{organisationId:id}]);
    }
}