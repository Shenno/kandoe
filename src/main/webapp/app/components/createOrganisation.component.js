System.register(['angular2/core', 'angular2/router', '../entity/organisation', "../service/userService"], function(exports_1) {
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
    var core_1, router_1, organisation_1, userService_1;
    var CreateOrganisationComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (organisation_1_1) {
                organisation_1 = organisation_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            }],
        execute: function() {
            CreateOrganisationComponent = (function () {
                function CreateOrganisationComponent(userService, router) {
                    var _this = this;
                    this.organisation = organisation_1.Organisation.createEmptyOrganisation();
                    this.router = router;
                    this.userService = userService;
                    this.userService.getMyDetails().subscribe(function (user) {
                        _this.organisation.organisatorId = user.id;
                    });
                    document.title = 'Maak organisatie aan';
                }
                CreateOrganisationComponent.prototype.onSubmit = function () {
                    this.userService.addOrganisation(this.organisation);
                };
                CreateOrganisationComponent.prototype.onCancel = function (event) {
                    event.preventDefault();
                    this.router.navigate(['/Organisations']);
                };
                CreateOrganisationComponent = __decorate([
                    core_1.Component({
                        selector: 'create-organisation',
                        templateUrl: 'app/partials_html/createOrganisation.component.html',
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [userService_1.UserService, router_1.Router])
                ], CreateOrganisationComponent);
                return CreateOrganisationComponent;
            })();
            exports_1("CreateOrganisationComponent", CreateOrganisationComponent);
        }
    }
});
//# sourceMappingURL=createOrganisation.component.js.map