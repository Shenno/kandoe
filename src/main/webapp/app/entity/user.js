System.register([], function(exports_1) {
    var User;
    return {
        setters:[],
        execute: function() {
            User = (function () {
                function User(id, username) {
                    this.id = id;
                    this.username = username;
                }
                User.createEmptyUser = function () {
                    return new User(0, "");
                };
                return User;
            })();
            exports_1("User", User);
        }
    }
});
//# sourceMappingURL=user.js.map