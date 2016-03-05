System.register([], function(exports_1) {
    var Tag;
    return {
        setters:[],
        execute: function() {
            Tag = (function () {
                function Tag(name) {
                    this.name = name;
                }
                Tag.createEmptyTag = function () {
                    return new Tag("");
                };
                return Tag;
            })();
            exports_1("Tag", Tag);
        }
    }
});
//# sourceMappingURL=tag.js.map