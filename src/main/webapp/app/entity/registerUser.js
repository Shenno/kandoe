System.register([], function(exports_1) {
    var RegisterUser;
    return {
        setters:[],
        execute: function() {
            RegisterUser = (function () {
                function RegisterUser(username, password, firstName, lastName) {
                    this.username = username;
                    this.password = password;
                    this.firstName = firstName;
                    this.lastName = lastName;
                }
                return RegisterUser;
            })();
            exports_1("RegisterUser", RegisterUser);
        }
    }
});
//# sourceMappingURL=registerUser.js.map