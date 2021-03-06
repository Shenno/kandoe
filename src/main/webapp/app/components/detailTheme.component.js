System.register(['angular2/core', 'angular2/router', '../entity/theme', "../service/contentService", "angular2/common", "../service/userService"], function(exports_1, context_1) {
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
    var core_1, router_1, theme_1, contentService_1, common_1, common_2, userService_1;
    var DetailThemeComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (theme_1_1) {
                theme_1 = theme_1_1;
            },
            function (contentService_1_1) {
                contentService_1 = contentService_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
                common_2 = common_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            }],
        execute: function() {
            /**
             * Component for showing details about Theme
             */
            DetailThemeComponent = (function () {
                function DetailThemeComponent(contentService, userService, routeParam, router) {
                    var _this = this;
                    this.currentUsername = "";
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.cards = [];
                    this.router = router;
                    this.contentService = contentService;
                    this.userService = userService;
                    contentService.getTheme(routeParam.params["themeId"]).subscribe(function (theme) {
                        _this.theme = theme;
                        document.title = 'Thema: ' + _this.theme.themeName;
                    });
                    contentService.getCardsByThemeId(routeParam.params["themeId"]).subscribe(function (cards) {
                        _this.cards = cards;
                    });
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.currentUsername = user.username;
                    });
                }
                DetailThemeComponent.prototype.onSubmit = function (event) {
                    event.preventDefault(); //prevents weird question mark url bug
                    this.router.navigate(['/EditTheme', { themeId: this.theme.themeId }]);
                };
                DetailThemeComponent.prototype.detailCard = function (id) {
                    this.router.navigate(['/DetailCard', { cardId: id }]);
                };
                DetailThemeComponent.prototype.addCard = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/CreateCard', { themeId: this.theme.themeId }]);
                };
                DetailThemeComponent.prototype.navigateToDashboard = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/Dashboard', { themeId: this.theme.themeId }]);
                };
                DetailThemeComponent.prototype.backToList = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/Organisation', { organisationId: this.theme.organisationId }]);
                };
                DetailThemeComponent = __decorate([
                    core_1.Component({
                        selector: 'detail-theme',
                        templateUrl: 'app/partials_html/detailTheme.component.html',
                        encapsulation: core_1.ViewEncapsulation.None,
                        directives: [common_1.CORE_DIRECTIVES, common_2.FORM_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, userService_1.UserService, router_1.RouteParams, router_1.Router])
                ], DetailThemeComponent);
                return DetailThemeComponent;
            }());
            exports_1("DetailThemeComponent", DetailThemeComponent);
        }
    }
});
//# sourceMappingURL=detailTheme.component.js.map