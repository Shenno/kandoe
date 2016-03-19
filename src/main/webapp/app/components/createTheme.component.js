System.register(['angular2/core', 'angular2/router', '../entity/theme', "../service/contentService", "../entity/user", "../service/userService", "angular2/common"], function(exports_1, context_1) {
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
    var core_1, router_1, theme_1, contentService_1, user_1, userService_1, common_1, common_2;
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
            function (user_1_1) {
                user_1 = user_1_1;
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
                    this.tagErrorMessage = "";
                    this.organisatorErrorMessage = "";
                    this.currentUser = user_1.User.createEmptyUser();
                    document.title = 'Maak thema aan';
                    this.router = router;
                    this.contentService = contentService;
                    this.userService = userService;
                    this.theme.organisationId = +routeParam.params["organisationId"];
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.currentUser = user;
                        _this.theme.organisatorId = user.id;
                        _this.userService.getAllUsernames().subscribe(function (users) {
                            var index = users.indexOf(_this.currentUser.username);
                            users.splice(index, 1); //you can't add yourself as organisator
                            _this.users = users;
                        });
                    });
                }
                CreateThemeComponent.prototype.onEditThemeName = function () {
                    this.theme.errorMessage = "";
                };
                CreateThemeComponent.prototype.onAddTag = function () {
                    if (this.newTag != '') {
                        var tags = this.newTag.split(" ");
                        for (var i in tags) {
                            var tag = tags[i].toLowerCase();
                            if (this.theme.tags.indexOf(tag) == -1) {
                                this.theme.tags[this.theme.tags.length] = tag;
                                this.tagErrorMessage = '';
                            }
                            else {
                                this.tagErrorMessage = 'Tag "' + tag + '" bestaat al';
                            }
                        }
                        this.newTag = "";
                    }
                    else {
                        this.tagErrorMessage = 'Tag mag niet leeg zijn';
                    }
                };
                CreateThemeComponent.prototype.onRemoveTag = function (i) {
                    this.theme.tags.splice(i, 1);
                };
                CreateThemeComponent.prototype.onAddOrganisator = function () {
                    if (this.newOrganisator != '') {
                        this.theme.organisatorNames.push(this.newOrganisator);
                        var index = this.users.indexOf(this.newOrganisator);
                        this.users.splice(index, 1);
                        this.organisatorErrorMessage = '';
                        this.newOrganisator = "";
                    }
                    else {
                        this.organisatorErrorMessage = 'Gekozen organisator mag niet leeg zijn';
                    }
                };
                CreateThemeComponent.prototype.onRemoveOrganisator = function (i) {
                    this.users.push(this.theme.organisatorNames[i]);
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
            }());
            exports_1("CreateThemeComponent", CreateThemeComponent);
        }
    }
});
//# sourceMappingURL=createTheme.component.js.map