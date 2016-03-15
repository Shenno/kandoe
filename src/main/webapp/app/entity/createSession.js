System.register([], function(exports_1) {
    var createSession;
    return {
        setters:[],
        execute: function() {
            //test
            createSession = (function () {
                function createSession(participantsEmails, cardIds, themeId, nameSession, amountOfCircles) {
                    this.participantsEmails = participantsEmails;
                    this.cardIds = cardIds;
                    this.themeId = themeId;
                    this.nameSession = nameSession;
                    this.amountOfCircles = amountOfCircles;
                }
                return createSession;
            })();
            exports_1("createSession", createSession);
        }
    }
});
//# sourceMappingURL=createSession.js.map