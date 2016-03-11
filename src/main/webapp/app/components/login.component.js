System.register(['angular2/core', 'angular2/router', "../service/userService", "../entity/loginUser"], function(exports_1) {
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
    var core_1, router_1, userService_1, loginUser_1;
    var LoginComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            },
            function (loginUser_1_1) {
                loginUser_1 = loginUser_1_1;
            }],
        execute: function() {
            LoginComponent = (function () {
                function LoginComponent(router, userService) {
                    this.user = new loginUser_1.LoginUser("", "");
                    this.userService = userService;
                    this.router = router;
                }
                LoginComponent.prototype.getLucky = function () {
                    var _this = this;
                    this.userService.login(this.user)
                        .subscribe(function (res) {
                        localStorage.setItem("jwt", res.text());
                        _this.router.navigate(['/Home']);
                    }, function (error) {
                        console.log(error);
                    });
                };
                LoginComponent.prototype.getUnlucky = function () {
                    localStorage.removeItem("jwt");
                };
                LoginComponent = __decorate([
                    core_1.Component({
                        selector: 'login',
                        template: "\n    <div>\n        <label for=\"ib_username\">E-mailadres</label>\n        <input class=\"form-control\" id=\"ib_username\" name=\"ib_username\" [(ngModel)]=\"user.username\"/>\n    </div>\n\n    <div>\n        <label for=\"ib_password\">Paswoord</label>\n        <input class=\"form-control\" type=\"password\" id=\"ib_password\" name=\"ib_password\" [(ngModel)]=\"user.password\"/>\n    </div>\n    <br>\n    <button class=\"btn btn-primary\" name=\"btn_login\" (click)=\"getLucky()\" >Get lucky</button>\n    <button class=\"btn btn-default\" name=\"btn_logout\" (click)=\"getUnlucky()\">Get unlucky</button>\n\n    ",
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [router_1.Router, userService_1.UserService])
                ], LoginComponent);
                return LoginComponent;
            })();
            exports_1("LoginComponent", LoginComponent);
        }
    }
});
//# sourceMappingURL=login.component.js.map