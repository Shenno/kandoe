import {Theme} from "./theme";
export class Card {
    constructor(public id: number,
                public text: string,
                public imageURL: string,
                public priority: number,
                public theme: Theme) {

    }

    public static createEmptyCard(): Card {
        return new Card(0, "", "", 0, null);
    }
}