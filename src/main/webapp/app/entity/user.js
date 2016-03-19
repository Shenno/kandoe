System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var User;
    return {
        setters:[],
        execute: function() {
            User = (function () {
                function User(id, username, firstName) {
                    this.id = id;
                    this.username = username;
                    this.firstName = firstName;
                }
                User.createEmptyUser = function () {
                    return new User(0, "", "");
                };
                return User;
            }());
            exports_1("User", User);
        }
    }
});
//# sourceMappingURL=user.js.map