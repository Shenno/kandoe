System.register([], function(exports_1) {
    var Logger;
    return {
        setters:[],
        execute: function() {
            Logger = (function () {
                function Logger() {
                }
                Logger.prototype.log = function (message) {
                    console.log(message);
                };
                return Logger;
            })();
            exports_1("Logger", Logger);
        }
    }
});
//# sourceMappingURL=logger.js.map