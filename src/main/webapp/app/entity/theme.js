System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Theme;
    return {
        setters:[],
        execute: function() {
            /**
             * An object for showing and creating and editing a Theme
             */
            Theme = (function () {
                function Theme(themeId, themeName, description, commentaryAllowed, addingAdmitted, organisationId, organisatorId, organisatorNames, tags, errorMessage) {
                    this.themeId = themeId;
                    this.themeName = themeName;
                    this.description = description;
                    this.commentaryAllowed = commentaryAllowed;
                    this.addingAdmitted = addingAdmitted;
                    this.organisationId = organisationId;
                    this.organisatorId = organisatorId;
                    this.organisatorNames = organisatorNames;
                    this.tags = tags;
                    this.errorMessage = errorMessage;
                }
                Theme.createEmptyTheme = function () {
                    return new Theme(0, "", "", false, false, 0, 0, [], [], "");
                };
                return Theme;
            }());
            exports_1("Theme", Theme);
        }
    }
});
//# sourceMappingURL=theme.js.map