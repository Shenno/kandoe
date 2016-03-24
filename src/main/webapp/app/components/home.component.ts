import {Component,  ViewEncapsulation} from 'angular2/core';
import {Router, RouteParams} from 'angular2/router';
import {CORE_DIRECTIVES} from "angular2/common";
import {FORM_DIRECTIVES} from "angular2/common";
import {User} from "../entity/user";
import {SessionActive} from "../entity/sessionActive";
import {SessionService} from "../service/sessionService";
import {UserService} from "../service/userService";

/**
 * Component for Home page
 */
@Component({
selector: 'home',
templateUrl: 'app/partials_html/home.component.html',
encapsulation: ViewEncapsulation.None,
directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})

export class HomeComponent {
    private router:Router;
    private userService:UserService;
    private sessionService:SessionService;
    private userId = 0;
    private sessions:SessionActive[] = [];
    private currentUserDetails: User;

    public constructor(userService:UserService,sessionService:SessionService, routeParam:RouteParams, router:Router) {
        document.title = 'Kandoe';
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
        this.getCurrentUserDetails();
        userService.authenticationEvent$.subscribe((eventType: string) => {
            this.onAuthenticationEvent(eventType);
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

    public onAuthenticationEvent(eventType: string) {
        this.getCurrentUserDetails();
    }

    public getCurrentUserDetails() {
        this.userService.getMyDetails().subscribe(
            (user: User) => this.currentUserDetails = user,
            (err) => this.currentUserDetails = null);
    }
}