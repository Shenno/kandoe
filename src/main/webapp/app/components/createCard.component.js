System.register(['angular2/core', 'angular2/router', "../service/contentService", "../entity/card"], function(exports_1) {
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
    var core_1, router_1, contentService_1, card_1;
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
            }],
        execute: function() {
            CreateCardComponent = (function () {
                //new Promise<Theme[]>(resolve => setTimeout(() =>resolve(Theme), 2000));
                function CreateCardComponent(contentService, router) {
                    this.card = card_1.Card.createEmptyCard();
                    this.router = router;
                    this.contentService = contentService;
                    document.title = 'Maak kaartje aan';
                }
                CreateCardComponent.prototype.onSubmit = function () {
                    //TODO: id teruggeven en gebruiken om te navigeren
                    this.contentService.addCard(this.card);
                    //  this.router.navigate(['/Theme', {id: 1}]);
                };
                CreateCardComponent = __decorate([
                    core_1.Component({
                        selector: 'create-card',
                        templateUrl: 'app/partials_html/createCard.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, router_1.Router])
                ], CreateCardComponent);
                return CreateCardComponent;
            })();
            exports_1("CreateCardComponent", CreateCardComponent);
        }
    }
});
//# sourceMappingURL=createCard.component.js.map