System.register([], function(exports_1) {
    var Card;
    return {
        setters:[],
        execute: function() {
            Card = (function () {
                function Card(id, text, imageURL, themeId) {
                    this.id = id;
                    this.text = text;
                    this.imageURL = imageURL;
                    this.themeId = themeId;
                }
                Card.createEmptyCard = function () {
                    return new Card(0, "", "", 0);
                };
                return Card;
            })();
            exports_1("Card", Card);
        }
    }
});
//# sourceMappingURL=card.js.map