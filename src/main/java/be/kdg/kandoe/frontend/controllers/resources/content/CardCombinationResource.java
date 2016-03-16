package be.kdg.kandoe.frontend.controllers.resources.content;

import be.kdg.kandoe.frontend.controllers.resources.sessions.CardSessionResource;

import java.util.List;

/**
 * @author Devi Bral
 * @version 1.0 15/03/2016 14:08
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
