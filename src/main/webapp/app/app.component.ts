import {Component, ViewEncapsulation} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS} from 'angular2/router';
import {CirkelsessieComponent} from "./components/cirkelsessie.component";
import {CreateThemeComponent} from "./components/createTheme.component";

@Component({
    selector: 'my-app',
    templateUrl: 'app/partials_html/app.component.html',
    styleUrls: ['app/partials_css/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    encapsulation: ViewEncapsulation.None
})
@RouteConfig([
    {path:'/test', name: 'Test', component: CirkelsessieComponent},
    {path:'/createTheme',name:'CreateTheme',component: CreateThemeComponent}
])

export class AppComponent {
}