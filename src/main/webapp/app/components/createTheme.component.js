System.register(['angular2/core', 'angular2/router', '../entity/theme', "../service/contentService"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, theme_1, contentService_1;
    var CreateThemeComponent;
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
            }],
        execute: function() {
            CreateThemeComponent = (function () {
                //new Promise<Theme[]>(resolve => setTimeout(() =>resolve(Theme), 2000));
                function CreateThemeComponent(contentService, router) {
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.router = router;
                    this.contentService = contentService;
                    document.title = 'Maak thema aan';
                }
                CreateThemeComponent.prototype.onSubmit = function () {
                    //TODO: id teruggeven en gebruiken om hier te navigeren
                    this.contentService.addTheme(this.theme);
                    //  this.router.navigate(['/Theme', {id: 1}]);
                };
                CreateThemeComponent = __decorate([
                    core_1.Component({
                        selector: 'create-theme',
                        templateUrl: 'app/partials_html/createTheme.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, router_1.Router])
                ], CreateThemeComponent);
                return CreateThemeComponent;
            })();
            exports_1("CreateThemeComponent", CreateThemeComponent);
        }
    }
});
//# sourceMappingURL=createTheme.component.js.map