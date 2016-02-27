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
import {Logger} from "./util/logger";

bootstrap(AppComponent, [
    Logger,
    UrlService,
    ContentService,
    HTTP_PROVIDERS,
    ROUTER_PROVIDERS,
    provide(LocationStrategy,
        {useClass: HashLocationStrategy}) // .../#/crisis-center/
]);
