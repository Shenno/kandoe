System.register([], function(exports_1) {
    var LoginUser;
    return {
        setters:[],
        execute: function() {
            LoginUser = (function () {
                function LoginUser(username, password) {
                    this.username = username;
                    this.password = password;
                }
                return LoginUser;
            })();
            exports_1("LoginUser", LoginUser);
        }
    }
});
//# sourceMappingURL=loginUser.js.map