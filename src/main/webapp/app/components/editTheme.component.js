System.register(['angular2/core', 'angular2/router', "../service/contentService", "../entity/theme", "../service/userService", "../entity/user"], function(exports_1, context_1) {
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
    var core_1, router_1, contentService_1, theme_1, userService_1, user_1;
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
            },
            function (user_1_1) {
                user_1 = user_1_1;
            }],
        execute: function() {
            /**
             * Component edit page for editing Theme
             */
            EditThemeComponent = (function () {
                function EditThemeComponent(contentService, userService, router, routeParam) {
                    var _this = this;
                    //private tag: Tag = Tag.createEmptyTag();
                    this.theme = theme_1.Theme.createEmptyTheme();
                    this.newTag = "";
                    this.users = [];
                    this.newOrganisator = "";
                    this.currentUser = user_1.User.createEmptyUser();
                    this.tagErrorMessage = "";
                    this.organisatorErrorMessage = "";
                    this.router = router;
                    this.contentService = contentService;
                    this.userService = userService;
                    this.contentService.getTheme(routeParam.params["themeId"]).subscribe(function (theme) {
                        _this.theme = theme;
                        _this.theme.errorMessage = '';
                        document.title = 'Wijzig thema';
                    });
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.currentUser = user;
                        _this.userService.getAllUsernames().subscribe(function (users) {
                            var index = users.indexOf(_this.currentUser.username);
                            users.splice(index, 1); //you can't add yourself as organisator
                            _this.users = users;
                            index = _this.theme.organisatorNames.indexOf(_this.currentUser.username);
                            _this.theme.organisatorNames.splice(index, 1);
                        });
                    });
                }
                EditThemeComponent.prototype.onEditThemeName = function () {
                    this.theme.errorMessage = "";
                };
                EditThemeComponent.prototype.onAddTag = function () {
                    if (this.newTag != '') {
                        var tags = this.newTag.split(" ");
                        for (var i in tags) {
                            var tag = tags[i].toLowerCase();
                            if (this.theme.tags.indexOf(tag) == -1) {
                                this.theme.tags[this.theme.tags.length] = tag;
                                this.tagErrorMessage = '';
                            }
                            else {
                                this.tagErrorMessage = 'Tag "' + tag + '" already exists';
                            }
                        }
                        this.newTag = "";
                    }
                    else {
                        this.tagErrorMessage = 'Tag cannot be empty';
                    }
                };
                EditThemeComponent.prototype.onRemoveTag = function (i) {
                    this.theme.tags.splice(i, 1);
                };
                EditThemeComponent.prototype.onAddOrganisator = function () {
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
                EditThemeComponent.prototype.onRemoveOrganisator = function (i) {
                    this.users.push(this.theme.organisatorNames[i]);
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
            }());
            exports_1("EditThemeComponent", EditThemeComponent);
        }
    }
});
//# sourceMappingURL=editTheme.component.js.map