System.register([], function(exports_1) {
    var createSession;
    return {
        setters:[],
        execute: function() {
            createSession = (function () {
                function createSession(participantsEmails, cardIds, themeId) {
                    this.participantsEmails = participantsEmails;
                    this.cardIds = cardIds;
                    this.themeId = themeId;
                }
                return createSession;
            })();
            exports_1("createSession", createSession);
        }
    }
});
//# sourceMappingURL=createSession.js.map