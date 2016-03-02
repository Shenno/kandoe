import {bootstrap}    from 'angular2/platform/browser';
import {AppComponent} from './app.component';
import {ROUTER_PROVIDERS} from 'angular2/router';
import {provide}           from 'angular2/core';
import {LocationStrategy,
    HashLocationStrategy} from 'angular2/router';
import 'rxjs/add/operator/map';
import {HTTP_PROVIDERS} from "angular2/http";
import {UrlService} from "./service/urlService";
import {ContentService} from "./service/contentService";
import {UserService} from "./service/userService";
import {Logger} from "./util/logger";
import {AuthService} from "./service/authService";

bootstrap(AppComponent, [
    Logger,
    UrlService,
    ContentService,
    UserService,
    AuthService,
    HTTP_PROVIDERS,
    ROUTER_PROVIDERS,
    provide(LocationStrategy,
        {useClass: HashLocationStrategy}) // .../#/crisis-center/
]);
