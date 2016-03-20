import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {Organisation} from '../entity/organisation';
import {Http, Response, Headers} from "angular2/http";
import {UrlService} from "../service/urlService";
import {UserService} from "../service/userService";
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {User} from "../entity/user";
import {SessionActive} from "../entity/sessionActive";
import {SessionService} from "../service/sessionService";

/**
 * Component for an overview of Sessions
 */
@Component({
    selector: 'overview-session',
    templateUrl: 'app/partials_html/overviewSessions.component.html',
    encapsulation: ViewEncapsulation.None,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class OverviewSessionsComponent {

    private router:Router;

    private userService:UserService;
    private sessionService:SessionService;
    private userId = 0;
    private sessions:SessionActive[] = [];

    public constructor(userService:UserService,sessionService:SessionService, routeParam:RouteParams, router:Router) {
        this.router = router;
        this.userService = userService;
        this.sessionService = sessionService;
        this.userService.getMyDetails().subscribe((user:User) => {
            this.userId = user.id;
        });
        this.sessionService.getSessionsByUserId(this.userId).subscribe((sessions:SessionActive[]) => {
            this.sessions = sessions;
            document.title = 'Jouw sessies';
            var index = 0;
            for (index; index < sessions.length; index++) {
                this.getUser(index);
            }
        });
    }
    public getUser(id:number):void{
        var i = id;
        this.userService.getUserById(this.sessions[i].currentUser).subscribe((user:User) => {
            this.sessions[i].currentUserName = user.username;
        });
    }
    public onClickSession(id:string):void{
        this.router.navigate(['/Session',{sessionId:id}]);
    }
}