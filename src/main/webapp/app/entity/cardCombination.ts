import {Card} from "./card";
import {SessionCard} from "./sessionCard";

/**
 * An object for showing cardCombinations
 */
export class CardCombination {

    constructor(public cards: SessionCard[]) {

    }

    public static createEmptyCardCombination(): CardCombination {
        return new CardCombination([]);
    }
}