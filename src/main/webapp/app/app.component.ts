import {Component, ViewEncapsulation} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS} from 'angular2/router';
import {CirkelsessieComponent} from "./components/cirkelsessie.component";
import {CreateThemeComponent} from "./components/createTheme.component";
import {EditThemeComponent} from "./components/editTheme.component";
import {bootstrap} from "angular2/bootstrap";
import {DetailThemeComponent} from "./components/detailTheme.component";
import {CreateOrganisationComponent} from "./components/createOrganisation.component";
import {DetailOrganisationComponent} from "./components/detailOrganisation.component";
import {LoginComponent} from "./components/login.component";
import {UserService} from "./service/userService";
import {User} from "./entity/user";
import {OverviewOrganisationComponent} from "./components/overviewOrganisations.component";
import {RegisterComponent} from "./components/register.component";
import {ContentService} from "./service/contentService";
import {CreateSessionComponent} from "./components/createSession.component";
import {CreateCardComponent} from "./components/createCard.component";
import {HomeComponent} from "./components/home.component";
import {DashboardComponent} from "./components/dashboard.component";
import {ManageAccountComponent} from "./components/manageAccount.component";
import {SessionComponent} from "./components/session.component";
import {LogoutComponent} from "./components/logout.component";
import {DetailCardComponent} from "./components/detailCard.component";
import {OverviewSessionsComponent} from "./components/overviewSessions.component";
@Component({
    selector: 'my-app',
    templateUrl: 'app/partials_html/app.component.html',
    styleUrls: ['app/partials_css/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    encapsulation: ViewEncapsulation.None
})
@RouteConfig([
    {path:'/',name:'Home',component:HomeComponent},
    {path:'/home',name:'Home',component:HomeComponent},

    {path:'/dashboard',name:'Dashboard',component:DashboardComponent},

    {path:'/manageAccount',name:'ManageAccount',component:ManageAccountComponent},
    {path:'/login', name: 'Login', component: LoginComponent},
    {path:'/register', name: 'Register', component: RegisterComponent},
    {path:'/logout',name:'Logout',component:LogoutComponent},

    {path:'/createOrganisation',name:'CreateOrganisation',component: CreateOrganisationComponent},
    {path:'/organisation/:organisationId',name:'Organisation',component: DetailOrganisationComponent},
    {path:'/organisations',name:'Organisations',component:OverviewOrganisationComponent},

    {path:'/organisation/:organisationId/createTheme',name:'CreateTheme',component: CreateThemeComponent},
    {path:'/theme/:themeId', name:'Theme', component: DetailThemeComponent},
    {path:'/editTheme/:themeId',name:'EditTheme',component:EditThemeComponent},

    {path:'/theme/:themeId/createCard',name:'CreateCard',component:CreateCardComponent},
    {path:'/card/:cardId',name:'DetailCard',component: DetailCardComponent},

    {path:'/createSession', name:'CreateSession', component: CreateSessionComponent},
    {path:'/session/:sessionId', name:'Session',component:SessionComponent},
    {path:'/sessions', name:'Sessions', component:OverviewSessionsComponent},

    {path:'/cirkelsessie', name:'Cirkelsessie',component:CirkelsessieComponent}
])

export class AppComponent {

    userService:UserService;
    currentUserDetails: User;

    public constructor(userService:UserService) {
       /* this.userService = userService;
        userService.getMyDetails().subscribe(
            (user:User) => this.currentUserDetails = user,
            err => alert(localStorage.getItem("jwt") + err))*/
    }

}