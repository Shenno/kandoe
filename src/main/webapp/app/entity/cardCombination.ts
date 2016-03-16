import {Card} from "./card";
import {SessionCard} from "./sessionCard";
export class CardCombination {

    constructor(public cards: SessionCard[]) {

    }

    public static createEmptyCardCombination(): CardCombination {
        return new CardCombination([]);
    }
}