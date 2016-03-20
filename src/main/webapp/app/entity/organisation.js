System.register([], function(exports_1) {
    var Organisation;
    return {
        setters:[],
        execute: function() {
            /**
             * An object for showing and creating an Organisation
             */
            Organisation = (function () {
                function Organisation(id, name, organisatorId, organisatorName, themes, errorMessage) {
                    this.id = id;
                    this.name = name;
                    this.organisatorId = organisatorId;
                    this.organisatorName = organisatorName;
                    this.themes = themes;
                    this.errorMessage = errorMessage;
                }
                Organisation.createEmptyOrganisation = function () {
                    return new Organisation(0, "", 0, "", [], "");
                };
                return Organisation;
            })();
            exports_1("Organisation", Organisation);
        }
    }
});
//# sourceMappingURL=organisation.js.map