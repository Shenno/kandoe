package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.persistence.api.CardRepositoryCustom;
import be.kdg.kandoe.backend.persistence.api.TagRepositoryCustom;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by Len on 23-2-2016.
 */
@Repository("CardRepository")
public class CardRepositoryImpl implements CardRepositoryCustom {

        @PersistenceContext
        private EntityManager em;



    @Override
    public Card addCard(Card card) throws ContentServiceException {
        final TypedQuery<Card> q = em.createNamedQuery("Card.findByText", Card.class);
        q.setParameter("text", card.getText());

        if (!q.getResultList().isEmpty())
        {
            throw new ContentServiceException("Card " + card.getText() + " already exists");
        }
        final Session session = em.unwrap(Session.class);
        session.saveOrUpdate(card);
        return card;
    }
}
