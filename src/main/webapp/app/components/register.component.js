System.register(['angular2/core', "../service/userService", "../entity/registerUser"], function(exports_1) {
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
                        template: "\n    <form (submit)=\"register($event, username.value, password.value, firstName.value, lastName.value)\">\n\n        <label for=\"username\">Gebruikersnaam</label>\n        <!-- Using #username, we can identify this input to get the value on the form's submit event -->\n        <input type=\"text\" #username class=\"form-control\" id=\"username\" placeholder=\"Email\">\n        <br>\n        <label for=\"password\">Paswoord</label>\n        <!-- Using #password we can identify this input to get its value -->\n        <input type=\"password\" #password class=\"form-control\" id=\"password\" placeholder=\"Password\">\n        <br>\n        <label for=\"firstName\">Voornaam</label>\n        <input type=\"text\" #firstName class=\"form-control\" id=\"firstName\" placeholder=\"First name\">\n        <br>\n        <label for=\"lastName\">Naam</label>\n        <!-- Using #password we can identify this input to get its value -->\n        <input type=\"text\" #lastName class=\"form-control\" id=\"lastName\" placeholder=\"Last name\">\n        <br>\n        <button class=\"btn btn-primary\" type=\"submit\">Registreren</button>\n    </form>\n    ",
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