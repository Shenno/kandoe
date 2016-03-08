System.register([], function(exports_1) {
    var Theme;
    return {
        setters:[],
        execute: function() {
            Theme = (function () {
                function Theme(themeId, themeName, description, commentaryAllowed, addingAdmitted, organisationId, organisatorId, organisatorNames, tags) {
                    this.themeId = themeId;
                    this.themeName = themeName;
                    this.description = description;
                    this.commentaryAllowed = commentaryAllowed;
                    this.addingAdmitted = addingAdmitted;
                    this.organisationId = organisationId;
                    this.organisatorId = organisatorId;
                    this.organisatorNames = organisatorNames;
                    this.tags = tags;
                }
                Theme.createEmptyTheme = function () {
                    return new Theme(0, "", "", false, false, 0, 0, [], []);
                };
                return Theme;
            })();
            exports_1("Theme", Theme);
        }
    }
});
//# sourceMappingURL=theme.js.map