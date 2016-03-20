package be.kdg.kandoe.frontend.controllers.resources.content;

import be.kdg.kandoe.frontend.controllers.resources.sessions.CardSessionResource;

import java.util.List;

/**
 * CardCominationResource is a Resource object for CardCombinations
 */
public class CardCombinationResource {
    List<CardSessionResource> cards;

    public List<CardSessionResource> getCards() {
        return cards;
    }

    public void setCards(List<CardSessionResource> cards) {
        this.cards = cards;
    }
}
