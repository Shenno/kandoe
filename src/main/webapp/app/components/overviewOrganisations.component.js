System.register(['angular2/core', 'angular2/router', "../service/userService", "angular2/common"], function(exports_1) {
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
            OverviewOrganisationComponent = (function () {
                function OverviewOrganisationComponent(userService, routeParam, router) {
                    var _this = this;
                    //private organisation: Organisation = Organisation.createEmptyOrganisation();
                    this.organisations = [];
                    this.router = router;
                    this.userService = userService;
                    userService.getOrganisations().subscribe(function (organisations) {
                        _this.organisations = organisations;
                        document.title = 'Organisaties';
                    });
                }
                OverviewOrganisationComponent.prototype.addOrganisation = function () {
                    this.router.navigate(['/CreateOrganisation']);
                };
                OverviewOrganisationComponent.prototype.detailOrganisation = function (id) {
                    this.router.navigate(['/DetailOrganisation', { organisationId: id }]);
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
            })();
            exports_1("OverviewOrganisationComponent", OverviewOrganisationComponent);
        }
    }
});
//# sourceMappingURL=overviewOrganisations.component.js.map