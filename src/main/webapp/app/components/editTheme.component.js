System.register(['angular2/core', 'angular2/router', '../entity/tag', "../service/contentService", "angular2/router"], function(exports_1) {
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
    var core_1, router_1, tag_1, contentService_1, router_2;
    var EditThemeComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (tag_1_1) {
                tag_1 = tag_1_1;
            },
            function (contentService_1_1) {
                contentService_1 = contentService_1_1;
            },
            function (router_2_1) {
                router_2 = router_2_1;
            }],
        execute: function() {
            EditThemeComponent = (function () {
                function EditThemeComponent(contentService, routeParam, router) {
                    //this.router = router;
                    this.tag = tag_1.Tag.createEmptyTag();
                    this.contentService = contentService;
                }
                EditThemeComponent.prototype.onSubmit = function () {
                    this.contentService.addTag(this.tag);
                };
                EditThemeComponent = __decorate([
                    core_1.Component({
                        selector: 'edit-theme',
                        templateUrl: 'app/partials_html/editTheme.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [contentService_1.ContentService, router_2.RouteParams, router_1.Router])
                ], EditThemeComponent);
                return EditThemeComponent;
            })();
            exports_1("EditThemeComponent", EditThemeComponent);
        }
    }
});
//# sourceMappingURL=editTheme.component.js.map