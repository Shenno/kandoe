System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Tag;
    return {
        setters:[],
        execute: function() {
            /**
             * An object for a Tag
             */
            Tag = (function () {
                function Tag(name) {
                    this.name = name;
                }
                Tag.createEmptyTag = function () {
                    return new Tag("");
                };
                return Tag;
            }());
            exports_1("Tag", Tag);
        }
    }
});
//# sourceMappingURL=tag.js.map