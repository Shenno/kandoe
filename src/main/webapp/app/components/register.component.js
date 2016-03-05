System.register(['angular2/core', "../service/userService", "../entity/registerUser"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, userService_1, registerUser_1;
    var RegisterComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (userService_1_1) {
                userService_1 = userService_1_1;
            },
            function (registerUser_1_1) {
                registerUser_1 = registerUser_1_1;
            }],
        execute: function() {
            RegisterComponent = (function () {
                function RegisterComponent(userService) {
                    this.userService = userService;
                }
                RegisterComponent.prototype.register = function (event, username, password, firstName, lastName) {
                    event.preventDefault();
                    var registerUser = new registerUser_1.RegisterUser(username, password, firstName, lastName);
                    this.userService.register(registerUser);
                    /*this.userService.register()
                    fetch('http://localhost:3001/sessions/create', {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            username, password
                        })
                    })
                        .then(status)
                        .then(json)
                        .then((response) => {
                            // Once we get the JWT in the response, we save it into localStorage
                            localStorage.setItem('jwt', response.id_token);
                            // and then we redirect the user to the home
                            this.router.parent.navigate('/home');
                        })
                        .catch((error) => {
                            alert(error.message);
                            console.log(error.message);
                        });*/
                };
                RegisterComponent = __decorate([
                    core_1.Component({
                        selector: 'login',
                        template: "\n    <form (submit)=\"register($event, username.value, password.value, firstName.value, lastName.value)\">\n\n        <label for=\"username\">Username</label>\n        <!-- Using #username, we can identify this input to get the value on the form's submit event -->\n        <input type=\"text\" #username class=\"form-control\" id=\"username\" placeholder=\"Email\">\n\n        <label for=\"password\">Password</label>\n        <!-- Using #password we can identify this input to get its value -->\n        <input type=\"password\" #password class=\"form-control\" id=\"password\" placeholder=\"Password\">\n\n        <label for=\"firstName\">First name</label>\n        <input type=\"text\" #firstName class=\"form-control\" id=\"firstName\" placeholder=\"First name\">\n\n        <label for=\"lastName\">Last name</label>\n        <!-- Using #password we can identify this input to get its value -->\n        <input type=\"text\" #lastName class=\"form-control\" id=\"lastName\" placeholder=\"Last name\">\n\n        <button type=\"submit\">Submit</button>\n    </form>\n    ",
                        encapsulation: core_1.ViewEncapsulation.None
                    }), 
                    __metadata('design:paramtypes', [userService_1.UserService])
                ], RegisterComponent);
                return RegisterComponent;
            })();
            exports_1("RegisterComponent", RegisterComponent);
        }
    }
});
//# sourceMappingURL=register.component.js.map