System.register(['angular2/core', 'angular2/router', "angular2/common", "./apriori.component", "angular2/router"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, common_1, common_2, apriori_component_1, router_2;
    var DashboardComponent;
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
            function (apriori_component_1_1) {
                apriori_component_1 = apriori_component_1_1;
            },
            function (router_2_1) {
                router_2 = router_2_1;
            }],
        execute: function() {
            DashboardComponent = (function () {
                function DashboardComponent(router, routeParam) {
                    this.router = router;
                    this.routeParam = routeParam;
                }
                DashboardComponent.prototype.returnToTheme = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/Theme', { themeId: this.routeParam.params["themeId"] }]);
                };
                DashboardComponent = __decorate([
                    core_1.Component({
                        selector: 'dashboard',
                        directives: [common_1.CORE_DIRECTIVES, common_2.FORM_DIRECTIVES, apriori_component_1.AprioriComponent],
                        templateUrl: 'app/partials_html/dashboard.component.html',
                        encapsulation: core_1.ViewEncapsulation.None,
                    }), 
                    __metadata('design:paramtypes', [router_1.Router, router_2.RouteParams])
                ], DashboardComponent);
                return DashboardComponent;
            })();
            exports_1("DashboardComponent", DashboardComponent);
        }
    }
});
//# sourceMappingURL=dashboard.component.js.map