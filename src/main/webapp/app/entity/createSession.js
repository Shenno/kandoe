System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var createSession;
    return {
        setters:[],
        execute: function() {
            /**
             * An object for creating a Session
             */
            createSession = (function () {
                function createSession(participantsEmails, cardIds, themeId, nameSession, amountOfCircles) {
                    this.participantsEmails = participantsEmails;
                    this.cardIds = cardIds;
                    this.themeId = themeId;
                    this.nameSession = nameSession;
                    this.amountOfCircles = amountOfCircles;
                }
                return createSession;
            }());
            exports_1("createSession", createSession);
        }
    }
});
//# sourceMappingURL=createSession.js.map