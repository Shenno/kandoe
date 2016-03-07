export class Card {

    public selected: boolean;

    constructor(public id: number,
                public text: string,
                public imageURL: string,
                public themeId: number) {
        this.selected = true;

    }

    public static createEmptyCard(): Card {
        return new Card(0, "", "",  0);
    }
}