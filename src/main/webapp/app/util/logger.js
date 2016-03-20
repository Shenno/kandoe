System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Logger;
    return {
        setters:[],
        execute: function() {
            /**
             * Class to log everything
             */
            Logger = (function () {
                function Logger() {
                }
                Logger.prototype.log = function (message) {
                    console.log(message);
                };
                return Logger;
            }());
            exports_1("Logger", Logger);
        }
    }
});
//# sourceMappingURL=logger.js.map