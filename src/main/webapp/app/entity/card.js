System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Card;
    return {
        setters:[],
        execute: function() {
            /**
             * An object for creating and showing a card
             */
            Card = (function () {
                function Card(id, text, imageURL, themeId) {
                    this.id = id;
                    this.text = text;
                    this.imageURL = imageURL;
                    this.themeId = themeId;
                    this.selected = true;
                }
                Card.createEmptyCard = function () {
                    return new Card(0, "", "", 0);
                };
                return Card;
            }());
            exports_1("Card", Card);
        }
    }
});
//# sourceMappingURL=card.js.map