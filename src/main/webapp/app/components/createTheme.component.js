System.register(['angular2/core', '../entity/theme', "../service/contentService", "../entity/organisation", "../entity/user"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, theme_1, contentService_1, organisation_1, user_1;
    var CreateThemeComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (theme_1_1) {
                theme_1 = theme_1_1;
            },
            function (contentService_1_1) {
                contentService_1 = contentService_1_1;
            },
            function (organisation_1_1) {
                organisation_1 = organisation_1_1;
            },
            function (user_1_1) {
                user_1 = user_1_1;
            }],
        execute: function() {
            CreateThemeComponent = (function () {
                //new Promise<Theme[]>(resolve => setTimeout(() =>resolve(Theme), 2000));
                function CreateThemeComponent(contentService) {
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.contentService = contentService;
                    document.title = 'Maak thema aan';
                }
                CreateThemeComponent.prototype.onSubmit = function () {
                    this.theme.organisation = new organisation_1.Organisation(1, "Test", new user_1.User(1, "test"));
                    this.contentService.addTheme(this.theme);
                };
                CreateThemeComponent = __decorate([
                    core_1.Component({
                        selector: 'create-theme',
                        templateUrl: 'app/partials_html/createTheme.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService])
                ], CreateThemeComponent);
                return CreateThemeComponent;
            })();
            exports_1("CreateThemeComponent", CreateThemeComponent);
        }
    }
});
//# sourceMappingURL=createTheme.component.js.map