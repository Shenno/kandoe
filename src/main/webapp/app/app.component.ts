import {Component, ViewEncapsulation} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS} from 'angular2/router';
import {CirkelsessieComponent} from "./components/cirkelsessie.component";
import {CreateThemeComponent} from "./components/createTheme.component";
import {EditThemeComponent} from "./components/editTheme.component";
import {bootstrap} from "angular2/bootstrap";
import {DetailThemeComponent} from "./components/detailTheme.component";
import {CreateOrganisationComponent} from "./components/createOrganisation.component";

@Component({
    selector: 'my-app',
    templateUrl: 'app/partials_html/app.component.html',
    styleUrls: ['app/partials_css/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    encapsulation: ViewEncapsulation.None
})
@RouteConfig([
    {path:'/test', name: 'Test', component: CirkelsessieComponent},
    {path:'/createTheme',name:'CreateTheme',component: CreateThemeComponent},
    {path:'/detailTheme/:themeId', name:'DetailTheme', component: DetailThemeComponent},
    {path:'/editTheme/:themeId',name:'EditTheme',component:EditThemeComponent},
    {path:'/createOrganisation',name:'CreateOrganisation',component:CreateOrganisationComponent}
    //{path:'/detailOrganisation',name:'DetailOrganisation',component:DetailOrganisationComponent}

])

export class AppComponent {
}