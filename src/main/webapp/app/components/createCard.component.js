System.register(['angular2/core', 'angular2/router', "../service/contentService", "../entity/card", "angular2/router"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, contentService_1, card_1, router_2;
    var CreateCardComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (contentService_1_1) {
                contentService_1 = contentService_1_1;
            },
            function (card_1_1) {
                card_1 = card_1_1;
            },
            function (router_2_1) {
                router_2 = router_2_1;
            }],
        execute: function() {
            CreateCardComponent = (function () {
                function CreateCardComponent(contentService, router, routeParam) {
                    this.card = card_1.Card.createEmptyCard();
                    this.router = router;
                    this.contentService = contentService;
                    document.title = 'Maak kaartje aan';
                    this.card.themeId = +routeParam.params["themeId"];
                }
                CreateCardComponent.prototype.onSubmit = function () {
                    this.contentService.addCard(this.card);
                };
                CreateCardComponent.prototype.onCancel = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/Theme', { themeId: this.card.themeId }]);
                };
                CreateCardComponent = __decorate([
                    core_1.Component({
                        selector: 'create-card',
                        templateUrl: 'app/partials_html/createCard.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, router_1.Router, router_2.RouteParams])
                ], CreateCardComponent);
                return CreateCardComponent;
            })();
            exports_1("CreateCardComponent", CreateCardComponent);
        }
    }
});
//# sourceMappingURL=createCard.component.js.map