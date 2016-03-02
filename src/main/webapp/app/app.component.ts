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

@Component({
    selector: 'my-app',
    template: `<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <img alt="Brand" src="./favicon.ico">
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <!--<li class="active">
                    <a href="#">Link <span class="sr-only">(current)</span></a>
                </li>-->

                <li><a href="#" style="font-weight: bold"><span class="glyphicon glyphicon-play-circle"></span> Maak een cirkelsessie</a></li>
                <li><a href="#">Maak een organisatie</a></li>
                <li><a href="#">Thema's</a></li>
                <li><a href="#">Kaartjes</a></li>
            </ul>
            <!--
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>-->

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Registreren</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Mijn account <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Beheer</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Uitloggen</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
    <router-outlet></router-outlet>
    `,
    styleUrls: ['app/partials_css/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    encapsulation: ViewEncapsulation.None
})
@RouteConfig([
    {path:'/login', name: 'Login', component: LoginComponent},
    {path:'/test', name: 'Test', component: CirkelsessieComponent},
    {path:'/createTheme',name:'CreateTheme',component: CreateThemeComponent},
    {path:'/detailTheme/:themeId', name:'DetailTheme', component: DetailThemeComponent},
    {path:'/editTheme/:themeId',name:'EditTheme',component:EditThemeComponent},
    {path:'/createOrganisation',name:'CreateOrganisation',component: CreateOrganisationComponent},
    {path:'/detailOrganisation/:organisationId',name:'DetailOrganisation',component: DetailOrganisationComponent}

])

export class AppComponent {
}