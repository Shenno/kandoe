System.register(['angular2/core', 'angular2/router', '../entity/theme', "../service/contentService", "../service/userService", "angular2/common"], function(exports_1) {
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
    var core_1, router_1, theme_1, contentService_1, userService_1, common_1, common_2;
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
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
                common_2 = common_1_1;
            }],
        execute: function() {
            CreateThemeComponent = (function () {
                function CreateThemeComponent(contentService, userService, router, routeParam) {
                    var _this = this;
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.newTag = "";
                    this.users = [];
                    this.newOrganisator = "";
                    document.title = 'Maak thema aan';
                    this.router = router;
                    this.contentService = contentService;
                    this.theme.organisationId = +routeParam.params["organisationId"];
                    this.userService = userService;
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.theme.organisatorId = user.id;
                    });
                    this.userService.getAllUsernames().subscribe(function (users) {
                        _this.users = users;
                    });
                }
                CreateThemeComponent.prototype.onAddTag = function () {
                    this.theme.tags[this.theme.tags.length] = this.newTag.toLowerCase();
                    this.newTag = "";
                };
                CreateThemeComponent.prototype.onRemoveTag = function (i) {
                    this.theme.tags.splice(i, 1);
                };
                CreateThemeComponent.prototype.onAddOrganisator = function () {
                    this.theme.organisatorNames.push(this.newOrganisator);
                };
                CreateThemeComponent.prototype.onRemoveOrganisator = function (i) {
                    this.theme.organisatorNames.splice(i, 1);
                };
                CreateThemeComponent.prototype.onSubmit = function () {
                    this.contentService.addTheme(this.theme);
                };
                CreateThemeComponent.prototype.onCancel = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/Organisation', { organisationId: this.theme.organisationId }]);
                };
                CreateThemeComponent = __decorate([
                    core_1.Component({
                        selector: 'create-theme',
                        templateUrl: 'app/partials_html/createTheme.component.html',
                        encapsulation: core_1.ViewEncapsulation.None,
                        directives: [common_1.CORE_DIRECTIVES, common_2.FORM_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, userService_1.UserService, router_1.Router, router_1.RouteParams])
                ], CreateThemeComponent);
                return CreateThemeComponent;
            })();
            exports_1("CreateThemeComponent", CreateThemeComponent);
        }
    }
});
//# sourceMappingURL=createTheme.component.js.map