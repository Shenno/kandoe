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
import {OverviewThemeComponent} from "./components/overviewThemes.component";
import {OverviewCardComponent} from "./components/overviewCards.component";
import {CreateCardComponent} from "./components/createCard.component";
import {HomeComponent} from "./components/home.component";
import {DashboardComponent} from "./components/dashboard.component";
import {ManageAccountComponent} from "./components/manageAccount.component";
import {SessionComponent} from "./components/session.component";
import {LogoutComponent} from "./components/logout.component";

@Component({
    selector: 'my-app',
    templateUrl: 'app/partials_html/app.component.html',
    styleUrls: ['app/partials_css/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    encapsulation: ViewEncapsulation.None
})
@RouteConfig([
    {path:'/login', name: 'Login', component: LoginComponent},
    {path:'/register', name: 'Register', component: RegisterComponent},
    {path:'/test', name: 'Test', component: CirkelsessieComponent},
    {path:'/organisation/:organisationId/createTheme',name:'CreateTheme',component: CreateThemeComponent},
    {path:'/detailTheme/:themeId', name:'DetailTheme', component: DetailThemeComponent},
    {path:'/editTheme/:themeId',name:'EditTheme',component:EditThemeComponent},
    {path:'/createOrganisation',name:'CreateOrganisation',component: CreateOrganisationComponent},
    {path:'/createSession', name:'CreateSession', component: CreateSessionComponent},
    {path:'/detailTheme/:themeId/createCard',name:'CreateCard',component:CreateCardComponent},
    {path:'/detailOrganisation/:organisationId',name:'DetailOrganisation',component: DetailOrganisationComponent},
    {path:'/organisations',name:'Organisations',component:OverviewOrganisationComponent},
    {path:'/themes',name:'Themes',component:OverviewThemeComponent},
    {path:'/session/:sessionId', name:'Session',component:SessionComponent},
    {path:'/cards',name:'Cards',component:OverviewCardComponent},
    {path:'/createCard',name:'CreateCard',component:CreateCardComponent},
    {path:'/createTheme',name:'CreateTheme',component:CreateThemeComponent},
    {path:'/home',name:'Home',component:HomeComponent},
    {path:'/',name:'Home',component:HomeComponent},
    {path:'/dashboard',name:'Dashboard',component:DashboardComponent},
    {path:'/manageAccount',name:'ManageAccount',component:ManageAccountComponent},
    {path:'/logout',name:'Logout',component:LogoutComponent}
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