System.register(['angular2/core', 'angular2/router', "../service/userService", "angular2/common"], function(exports_1, context_1) {
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
    var core_1, router_1, userService_1, common_1, common_2;
    var OverviewOrganisationComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
                common_2 = common_1_1;
            }],
        execute: function() {
            /**
             * Component for an overview of organisations
             */
            OverviewOrganisationComponent = (function () {
                function OverviewOrganisationComponent(userService, routeParam, router) {
                    var _this = this;
                    this.userId = 0;
                    this.organisations = [];
                    this.router = router;
                    this.userService = userService;
                    userService.getOrganisations().subscribe(function (organisations) {
                        _this.organisations = organisations;
                        document.title = 'Organisaties';
                    });
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.userId = user.id;
                    });
                }
                OverviewOrganisationComponent.prototype.addOrganisation = function () {
                    this.router.navigate(['/CreateOrganisation']);
                };
                OverviewOrganisationComponent.prototype.detailOrganisation = function (id) {
                    this.router.navigate(['/Organisation', { organisationId: id }]);
                };
                OverviewOrganisationComponent = __decorate([
                    core_1.Component({
                        selector: 'overview-organisation',
                        templateUrl: 'app/partials_html/overviewOrganisations.component.html',
                        encapsulation: core_1.ViewEncapsulation.None,
                        directives: [common_1.CORE_DIRECTIVES, common_2.FORM_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [userService_1.UserService, router_1.RouteParams, router_1.Router])
                ], OverviewOrganisationComponent);
                return OverviewOrganisationComponent;
            }());
            exports_1("OverviewOrganisationComponent", OverviewOrganisationComponent);
        }
    }
});
//# sourceMappingURL=overviewOrganisations.component.js.map