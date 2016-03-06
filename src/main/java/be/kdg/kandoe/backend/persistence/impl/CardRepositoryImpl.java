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
import java.util.List;

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
        session.save(card);
        return card;
    }

    @Override
    public List<Card> getCardsByThemeId(int themeId) {
        final TypedQuery<Card> q = em.createNamedQuery("Card.findByThemeId", Card.class);
        q.setParameter("themeId", themeId);
        return q.getResultList();
    }


}

