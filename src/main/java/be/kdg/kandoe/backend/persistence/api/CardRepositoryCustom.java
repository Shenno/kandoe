package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;

import java.util.List;

/**
 * Extra interface to support customization of Spring's Data interfaces for {link: CardRepository}
 */
public interface CardRepositoryCustom {

   Card addCard(Card card) throws ContentServiceException;
   List<Card> getCardsByThemeId(int themeId);
}
