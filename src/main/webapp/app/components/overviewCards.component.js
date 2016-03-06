System.register(['angular2/core', 'angular2/router', "angular2/common", "../service/contentService"], function(exports_1) {
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
    var core_1, router_1, common_1, common_2, contentService_1;
    var OverviewCardComponent;
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
            function (contentService_1_1) {
                contentService_1 = contentService_1_1;
            }],
        execute: function() {
            OverviewCardComponent = (function () {
                function OverviewCardComponent(contentService, routeParam, router) {
                    var _this = this;
                    this.cards = [];
                    this.router = router;
                    this.contentService = contentService;
                    contentService.getCardsByThemeId(routeParam.get("themeId")).subscribe(function (cards) {
                        _this.cards = cards;
                        document.title = 'Kaartjes';
                    });
                }
                OverviewCardComponent.prototype.addCard = function () {
                    this.router.navigate(['/CreateCard']);
                };
                OverviewCardComponent = __decorate([
                    core_1.Component({
                        selector: 'overview-card',
                        templateUrl: 'app/partials_html/overviewCards.component.html',
                        encapsulation: core_1.ViewEncapsulation.None,
                        directives: [common_1.CORE_DIRECTIVES, common_2.FORM_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, router_1.RouteParams, router_1.Router])
                ], OverviewCardComponent);
                return OverviewCardComponent;
            })();
            exports_1("OverviewCardComponent", OverviewCardComponent);
        }
    }
});
//# sourceMappingURL=overviewCards.component.js.map