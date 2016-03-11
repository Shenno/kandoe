package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.persistence.api.SessionRepositoyCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author: Evelien
 * @versionon 1.0 11-3-201614:13
 */

@Repository("sessionRepository")
public class SessionRepositoryImpl implements SessionRepositoyCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Session> findSessionByUserId (int userId) {
        final TypedQuery<Session> q = em.createNamedQuery("Session.findSessionsByUserId",Session.class);
        q.setParameter("userId", userId);
        return q.getResultList();
    }

}
