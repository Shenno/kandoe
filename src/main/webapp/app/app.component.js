System.register(['angular2/core', 'angular2/router', "./components/createTheme.component", "./components/editTheme.component", "./components/detailTheme.component", "./components/createOrganisation.component", "./components/detailOrganisation.component", "./components/login.component", "./service/userService", "./components/overviewOrganisations.component", "./components/register.component", "./components/createSession.component", "./components/createCard.component", "./components/home.component", "./components/dashboard.component", "./components/manageAccount.component", "./components/session.component", "./components/logout.component", "./components/detailCard.component", "./components/overviewSessions.component", "./components/createCsv.component", "./components/error.component"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, createTheme_component_1, editTheme_component_1, detailTheme_component_1, createOrganisation_component_1, detailOrganisation_component_1, login_component_1, userService_1, overviewOrganisations_component_1, register_component_1, createSession_component_1, createCard_component_1, home_component_1, dashboard_component_1, manageAccount_component_1, session_component_1, logout_component_1, detailCard_component_1, overviewSessions_component_1, createCsv_component_1, error_component_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
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
            },
            function (session_component_1_1) {
                session_component_1 = session_component_1_1;
            },
            function (logout_component_1_1) {
                logout_component_1 = logout_component_1_1;
            },
            function (detailCard_component_1_1) {
                detailCard_component_1 = detailCard_component_1_1;
            },
            function (overviewSessions_component_1_1) {
                overviewSessions_component_1 = overviewSessions_component_1_1;
            },
            function (createCsv_component_1_1) {
                createCsv_component_1 = createCsv_component_1_1;
            },
            function (error_component_1_1) {
                error_component_1 = error_component_1_1;
            }],
        execute: function() {
            /**
             * Component for the app
             */
            AppComponent = (function () {
                function AppComponent(userService) {
                    var _this = this;
                    this.userService = userService;
                    this.getCurrentUserDetails();
                    userService.authenticationEvent$.subscribe(function (eventType) {
                        _this.onAuthenticationEvent(eventType);
                    });
                }
                AppComponent.prototype.onAuthenticationEvent = function (eventType) {
                    this.getCurrentUserDetails();
                };
                AppComponent.prototype.getCurrentUserDetails = function () {
                    var _this = this;
                    this.userService.getMyDetails().subscribe(function (user) { return _this.currentUserDetails = user; }, function (err) { return _this.currentUserDetails = null; });
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        templateUrl: 'app/partials_html/app.component.html',
                        styleUrls: ['app/partials_css/app.component.css'],
                        directives: [router_1.ROUTER_DIRECTIVES],
                        encapsulation: core_1.ViewEncapsulation.None
                    }),
                    router_1.RouteConfig([
                        { path: '/', name: 'Home', component: home_component_1.HomeComponent },
                        { path: '/home', name: 'Home', component: home_component_1.HomeComponent },
                        { path: '/theme/:themeId/dashboard', name: 'Dashboard', component: dashboard_component_1.DashboardComponent },
                        { path: '/manageAccount', name: 'ManageAccount', component: manageAccount_component_1.ManageAccountComponent },
                        { path: '/login', name: 'Login', component: login_component_1.LoginComponent },
                        { path: '/register', name: 'Register', component: register_component_1.RegisterComponent },
                        { path: '/logout', name: 'Logout', component: logout_component_1.LogoutComponent },
                        { path: '/createOrganisation', name: 'CreateOrganisation', component: createOrganisation_component_1.CreateOrganisationComponent },
                        { path: '/organisation/:organisationId', name: 'Organisation', component: detailOrganisation_component_1.DetailOrganisationComponent },
                        { path: '/organisations', name: 'Organisations', component: overviewOrganisations_component_1.OverviewOrganisationComponent },
                        { path: '/organisation/:organisationId/createTheme', name: 'CreateTheme', component: createTheme_component_1.CreateThemeComponent },
                        { path: '/theme/:themeId', name: 'Theme', component: detailTheme_component_1.DetailThemeComponent },
                        { path: '/editTheme/:themeId', name: 'EditTheme', component: editTheme_component_1.EditThemeComponent },
                        { path: '/theme/:themeId/createCard', name: 'CreateCard', component: createCard_component_1.CreateCardComponent },
                        { path: '/card/:cardId', name: 'DetailCard', component: detailCard_component_1.DetailCardComponent },
                        { path: '/createSession', name: 'CreateSession', component: createSession_component_1.CreateSessionComponent },
                        { path: '/session/:sessionId', name: 'Session', component: session_component_1.SessionComponent },
                        { path: '/sessions', name: 'Sessions', component: overviewSessions_component_1.OverviewSessionsComponent },
                        { path: '/createCsv', name: 'CreateCsv', component: createCsv_component_1.CreateCsvComponent },
                        { path: '/*error', name: 'Error', component: error_component_1.ErrorComponent }
                    ]), 
                    __metadata('design:paramtypes', [userService_1.UserService])
                ], AppComponent);
                return AppComponent;
            })();
            exports_1("AppComponent", AppComponent);
        }
    }
});
//# sourceMappingURL=app.component.js.map