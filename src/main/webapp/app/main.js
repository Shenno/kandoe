System.register(['angular2/platform/browser', './app.component', 'angular2/router', 'angular2/core', 'rxjs/add/operator/map', "angular2/http", "./service/urlService", "./service/contentService", "./service/userService", "./util/logger", "./service/sessionService"], function(exports_1) {
    var browser_1, app_component_1, router_1, core_1, router_2, http_1, urlService_1, contentService_1, userService_1, logger_1, sessionService_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (app_component_1_1) {
                app_component_1 = app_component_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
                router_2 = router_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (_1) {},
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (urlService_1_1) {
                urlService_1 = urlService_1_1;
            },
            function (contentService_1_1) {
                contentService_1 = contentService_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            },
            function (logger_1_1) {
                logger_1 = logger_1_1;
            },
            function (sessionService_1_1) {
                sessionService_1 = sessionService_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(app_component_1.AppComponent, [
                logger_1.Logger,
                urlService_1.UrlService,
                contentService_1.ContentService,
                userService_1.UserService,
                sessionService_1.SessionService,
                http_1.HTTP_PROVIDERS,
                router_1.ROUTER_PROVIDERS,
                core_1.provide(router_2.LocationStrategy, { useClass: router_2.HashLocationStrategy }) // .../#/crisis-center/
            ]);
        }
    }
});
//# sourceMappingURL=main.js.map