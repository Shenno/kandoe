System.register(['angular2/core', 'angular2/router', "angular2/common", "./apriori.component", "../entity/theme", "../service/contentService"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, common_1, common_2, apriori_component_1, theme_1, contentService_1;
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
            function (theme_1_1) {
                theme_1 = theme_1_1;
            },
            function (contentService_1_1) {
                contentService_1 = contentService_1_1;
            }],
        execute: function() {
            DashboardComponent = (function () {
                function DashboardComponent(contentService, router, routeParam) {
                    var _this = this;
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.router = router;
                    this.contentService = contentService;
                    this.contentService.getTheme(routeParam.params["themeId"]).subscribe(function (theme) {
                        _this.theme = theme;
                        document.title = 'Thema: ' + _this.theme.themeName + ' - Dashboard';
                    });
                }
                DashboardComponent.prototype.returnToTheme = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/Theme', { themeId: this.theme.themeId }]);
                };
                DashboardComponent = __decorate([
                    core_1.Component({
                        selector: 'dashboard',
                        templateUrl: 'app/partials_html/dashboard.component.html',
                        encapsulation: core_1.ViewEncapsulation.None,
                        directives: [common_1.CORE_DIRECTIVES, common_2.FORM_DIRECTIVES, apriori_component_1.AprioriComponent]
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, router_1.Router, router_1.RouteParams])
                ], DashboardComponent);
                return DashboardComponent;
            })();
            exports_1("DashboardComponent", DashboardComponent);
        }
    }
});
//# sourceMappingURL=dashboard.component.js.map