System.register(['angular2/core', 'angular2/router', "./components/cirkelsessie.component", "./components/createTheme.component", "./components/editTheme.component", "./components/detailTheme.component", "./components/createOrganisation.component", "./components/detailOrganisation.component", "./components/login.component", "./service/userService"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") return Reflect.decorate(decorators, target, key, desc);
        switch (arguments.length) {
            case 2: return decorators.reduceRight(function(o, d) { return (d && d(o)) || o; }, target);
            case 3: return decorators.reduceRight(function(o, d) { return (d && d(target, key)), void 0; }, void 0);
            case 4: return decorators.reduceRight(function(o, d) { return (d && d(target, key, o)) || o; }, desc);
        }
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, cirkelsessie_component_1, createTheme_component_1, editTheme_component_1, detailTheme_component_1, createOrganisation_component_1, detailOrganisation_component_1, login_component_1, userService_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (cirkelsessie_component_1_1) {
                cirkelsessie_component_1 = cirkelsessie_component_1_1;
            },
            function (createTheme_component_1_1) {
                createTheme_component_1 = createTheme_component_1_1;
            },
            function (editTheme_component_1_1) {
                editTheme_component_1 = editTheme_component_1_1;
            },
            function (detailTheme_component_1_1) {
                detailTheme_component_1 = detailTheme_component_1_1;
            },
            function (createOrganisation_component_1_1) {
                createOrganisation_component_1 = createOrganisation_component_1_1;
            },
            function (detailOrganisation_component_1_1) {
                detailOrganisation_component_1 = detailOrganisation_component_1_1;
            },
            function (login_component_1_1) {
                login_component_1 = login_component_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent(userService) {
                    /* this.userService = userService;
                     userService.getMyDetails().subscribe(
                         (user:User) => this.currentUserDetails = user,
                         err => alert(localStorage.getItem("jwt") + err))*/
                }
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        template: "<nav class=\"navbar navbar-default\">\n    <div class=\"container-fluid\">\n        <div class=\"navbar-header\">\n            <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\"\n                    data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\n                <span class=\"sr-only\">Toggle navigation</span>\n                <span class=\"icon-bar\"></span>\n                <span class=\"icon-bar\"></span>\n                <span class=\"icon-bar\"></span>\n            </button>\n            <a class=\"navbar-brand\" href=\"#\">\n                <img alt=\"Brand\" src=\"./favicon.ico\">\n            </a>\n        </div>\n        <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n            <ul class=\"nav navbar-nav\">\n                <li><a href=\"#\" style=\"font-weight: bold\"><span class=\"glyphicon glyphicon-play-circle\"></span> Maak een cirkelsessie</a></li>\n                <li><a href=\"#\">Maak een organisatie</a></li>\n                <li><a href=\"#\">Thema's</a></li>\n                <li><a href=\"#\">Kaartjes</a></li>\n            </ul>\n            <ul class=\"nav navbar-nav navbar-right\">\n                <li><a href=\"#\" *ngIf=\"currentUserDetails\">Registreren</a></li>\n                <li class=\"dropdown\">\n                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\"\n                       aria-expanded=\"false\">Mijn account <span class=\"caret\"></span></a>\n                    <ul class=\"dropdown-menu\">\n                        <li><a href=\"#\">Dashboard</a></li>\n                        <li><a href=\"#\">Beheer</a></li>\n                        <li role=\"separator\" class=\"divider\"></li>\n                        <li><a href=\"#\" (click)=\"userService.logout()\">Uitloggen</a></li>\n                    </ul>\n                </li>\n            </ul>\n        </div><!-- /.navbar-collapse -->\n    </div><!-- /.container-fluid -->\n</nav>\n    <router-outlet></router-outlet>\n    ",
                        styleUrls: ['app/partials_css/app.component.css'],
                        directives: [router_1.ROUTER_DIRECTIVES],
                        encapsulation: core_1.ViewEncapsulation.None
                    }),
                    router_1.RouteConfig([
                        { path: '/login', name: 'Login', component: login_component_1.LoginComponent },
                        { path: '/test', name: 'Test', component: cirkelsessie_component_1.CirkelsessieComponent },
                        { path: '/organisation/:organisationId/createTheme', name: 'CreateTheme', component: createTheme_component_1.CreateThemeComponent },
                        { path: '/detailTheme/:themeId', name: 'DetailTheme', component: detailTheme_component_1.DetailThemeComponent },
                        { path: '/editTheme/:themeId', name: 'EditTheme', component: editTheme_component_1.EditThemeComponent },
                        { path: '/createOrganisation', name: 'CreateOrganisation', component: createOrganisation_component_1.CreateOrganisationComponent },
                        { path: '/detailOrganisation/:organisationId', name: 'DetailOrganisation', component: detailOrganisation_component_1.DetailOrganisationComponent }
                    ]), 
                    __metadata('design:paramtypes', [userService_1.UserService])
                ], AppComponent);
                return AppComponent;
            })();
            exports_1("AppComponent", AppComponent); /*(theme:Theme) => {
             this.theme = theme;
             document.title = 'Wijzig thema';
             })*/
        }
    }
});
//# sourceMappingURL=app.component.js.map