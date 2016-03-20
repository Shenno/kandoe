System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var LoginUser;
    return {
        setters:[],
        execute: function() {
            /**
             * An object for login a user
             */
            LoginUser = (function () {
                function LoginUser(username, password) {
                    this.username = username;
                    this.password = password;
                }
                return LoginUser;
            }());
            exports_1("LoginUser", LoginUser);
        }
    }
});
//# sourceMappingURL=loginUser.js.map