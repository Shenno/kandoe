System.register([], function(exports_1) {
    var User;
    return {
        setters:[],
        execute: function() {
            /**
             * An object for a User
             */
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
            })();
            exports_1("User", User);
        }
    }
});
//# sourceMappingURL=user.js.map