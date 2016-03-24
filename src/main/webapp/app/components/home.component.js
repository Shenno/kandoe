System.register(['angular2/core', 'angular2/router', "angular2/common", "../service/sessionService", "../service/userService"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, common_1, common_2, sessionService_1, userService_1;
    var HomeComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
                common_2 = common_1_1;
            },
            function (sessionService_1_1) {
                sessionService_1 = sessionService_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            }],
        execute: function() {
            /**
             * Component for Home page
             */
            HomeComponent = (function () {
                function HomeComponent(userService, sessionService, routeParam, router) {
                    var _this = this;
                    this.userId = 0;
                    this.sessions = [];
                    document.title = 'Kandoe';
                    this.router = router;
                    this.userService = userService;
                    this.sessionService = sessionService;
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.userId = user.id;
                    });
                    this.sessionService.getSessionsByUserId(this.userId).subscribe(function (sessions) {
                        _this.sessions = sessions;
                        document.title = 'Jouw sessies';
                        var index = 0;
                        for (index; index < sessions.length; index++) {
                            _this.getUser(index);
                        }
                    });
                }
                HomeComponent.prototype.getUser = function (id) {
                    var _this = this;
                    var i = id;
                    this.userService.getUserById(this.sessions[i].currentUser).subscribe(function (user) {
                        _this.sessions[i].currentUserName = user.username;
                    });
                };
                HomeComponent.prototype.onClickSession = function (id) {
                    this.router.navigate(['/Session', { sessionId: id }]);
                };
                HomeComponent = __decorate([
                    core_1.Component({
                        selector: 'home',
                        templateUrl: 'app/partials_html/home.component.html',
                        encapsulation: core_1.ViewEncapsulation.None,
                        directives: [common_1.CORE_DIRECTIVES, common_2.FORM_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [userService_1.UserService, sessionService_1.SessionService, router_1.RouteParams, router_1.Router])
                ], HomeComponent);
                return HomeComponent;
            }());
            exports_1("HomeComponent", HomeComponent);
        }
    }
});
//# sourceMappingURL=home.component.js.map