System.register(['angular2/core', 'angular2/router', "./components/cirkelsessie.component", "./components/createTheme.component", "./components/editTheme.component", "./components/detailTheme.component", "./components/createOrganisation.component", "./components/detailOrganisation.component", "./components/login.component", "./service/userService", "./components/overviewOrganisations.component", "./components/register.component", "./components/createSession.component", "./components/overviewThemes.component", "./components/overviewCards.component", "./components/createCard.component", "./components/home.component", "./components/dashboard.component", "./components/manageAccount.component"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, cirkelsessie_component_1, createTheme_component_1, editTheme_component_1, detailTheme_component_1, createOrganisation_component_1, detailOrganisation_component_1, login_component_1, userService_1, overviewOrganisations_component_1, register_component_1, createSession_component_1, overviewThemes_component_1, overviewCards_component_1, createCard_component_1, home_component_1, dashboard_component_1, manageAccount_component_1;
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
            },
            function (overviewOrganisations_component_1_1) {
                overviewOrganisations_component_1 = overviewOrganisations_component_1_1;
            },
            function (register_component_1_1) {
                register_component_1 = register_component_1_1;
            },
            function (createSession_component_1_1) {
                createSession_component_1 = createSession_component_1_1;
            },
            function (overviewThemes_component_1_1) {
                overviewThemes_component_1 = overviewThemes_component_1_1;
            },
            function (overviewCards_component_1_1) {
                overviewCards_component_1 = overviewCards_component_1_1;
            },
            function (createCard_component_1_1) {
                createCard_component_1 = createCard_component_1_1;
            },
            function (home_component_1_1) {
                home_component_1 = home_component_1_1;
            },
            function (dashboard_component_1_1) {
                dashboard_component_1 = dashboard_component_1_1;
            },
            function (manageAccount_component_1_1) {
                manageAccount_component_1 = manageAccount_component_1_1;
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
                        templateUrl: 'app/partials_html/app.component.html',
                        styleUrls: ['app/partials_css/app.component.css'],
                        directives: [router_1.ROUTER_DIRECTIVES],
                        encapsulation: core_1.ViewEncapsulation.None
                    }),
                    router_1.RouteConfig([
                        { path: '/login', name: 'Login', component: login_component_1.LoginComponent },
                        { path: '/register', name: 'Register', component: register_component_1.RegisterComponent },
                        { path: '/test', name: 'Test', component: cirkelsessie_component_1.CirkelsessieComponent },
                        { path: '/organisation/:organisationId/createTheme', name: 'CreateTheme', component: createTheme_component_1.CreateThemeComponent },
                        { path: '/detailTheme/:themeId', name: 'DetailTheme', component: detailTheme_component_1.DetailThemeComponent },
                        { path: '/editTheme/:themeId', name: 'EditTheme', component: editTheme_component_1.EditThemeComponent },
                        { path: '/createOrganisation', name: 'CreateOrganisation', component: createOrganisation_component_1.CreateOrganisationComponent },
                        { path: '/createSession', name: 'CreateSession', component: createSession_component_1.CreateSessionComponent },
                        { path: '/detailOrganisation/:organisationId', name: 'DetailOrganisation', component: detailOrganisation_component_1.DetailOrganisationComponent },
                        { path: '/organisations', name: 'Organisations', component: overviewOrganisations_component_1.OverviewOrganisationComponent },
                        { path: '/themes', name: 'Themes', component: overviewThemes_component_1.OverviewThemeComponent },
                        { path: '/cards', name: 'Cards', component: overviewCards_component_1.OverviewCardComponent },
                        { path: '/createCard', name: 'CreateCard', component: createCard_component_1.CreateCardComponent },
                        { path: '/createTheme', name: 'CreateTheme', component: createTheme_component_1.CreateThemeComponent },
                        { path: '/home', name: 'Home', component: home_component_1.HomeComponent },
                        { path: '/', name: 'Home', component: home_component_1.HomeComponent },
                        { path: '/dashboard', name: 'Dashboard', component: dashboard_component_1.DashboardComponent },
                        { path: '/manageAccount', name: 'ManageAccount', component: manageAccount_component_1.ManageAccountComponent }
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