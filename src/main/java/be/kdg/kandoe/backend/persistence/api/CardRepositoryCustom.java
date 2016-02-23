package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;

/**
 * Created by Len on 23-2-2016.
 */
public interface CardRepositoryCustom {

   Card addCard(Card card) throws ContentServiceException;

}
