System.register(['angular2/core', 'angular2/router', "../service/contentService", "../entity/theme", "../service/userService"], function(exports_1) {
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
    var core_1, router_1, contentService_1, theme_1, userService_1;
    var EditThemeComponent;
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
            function (theme_1_1) {
                theme_1 = theme_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            }],
        execute: function() {
            EditThemeComponent = (function () {
                function EditThemeComponent(contentService, userService, router, routeParam) {
                    var _this = this;
                    //private tag: Tag = Tag.createEmptyTag();
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.newTag = "";
                    this.users = [];
                    this.newOrganisator = "";
                    this.currentUsername = "";
                    this.router = router;
                    this.contentService = contentService;
                    this.userService = userService;
                    this.contentService.getTheme(routeParam.params["themeId"]).subscribe(function (theme) {
                        _this.theme = theme;
                        document.title = 'Wijzig thema';
                    });
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.currentUsername = user.username;
                        _this.userService.getAllUsernames().subscribe(function (users) {
                            _this.users = users;
                            var i = users.indexOf(_this.currentUsername);
                            _this.users.splice(i, 1);
                            i = _this.theme.organisatorNames.indexOf(_this.currentUsername);
                            _this.theme.organisatorNames.splice(i, 1);
                        });
                    });
                }
                EditThemeComponent.prototype.onAddTag = function () {
                    this.theme.tags[this.theme.tags.length] = this.newTag.toLowerCase();
                    this.newTag = "";
                };
                EditThemeComponent.prototype.onRemoveTag = function (i) {
                    this.theme.tags.splice(i, 1);
                };
                EditThemeComponent.prototype.onAddOrganisator = function () {
                    this.theme.organisatorNames.push(this.newOrganisator);
                };
                EditThemeComponent.prototype.onRemoveOrganisator = function (i) {
                    this.theme.organisatorNames.splice(i, 1);
                };
                EditThemeComponent.prototype.onSubmit = function () {
                    this.contentService.updateTheme(this.theme);
                };
                EditThemeComponent.prototype.onCancel = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/Theme', { themeId: this.theme.themeId }]);
                };
                EditThemeComponent = __decorate([
                    core_1.Component({
                        selector: 'edit-theme',
                        templateUrl: 'app/partials_html/editTheme.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, userService_1.UserService, router_1.Router, router_1.RouteParams])
                ], EditThemeComponent);
                return EditThemeComponent;
            })();
            exports_1("EditThemeComponent", EditThemeComponent);
        }
    }
});
//# sourceMappingURL=editTheme.component.js.map