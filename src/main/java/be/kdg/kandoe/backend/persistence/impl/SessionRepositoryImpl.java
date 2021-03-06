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
 * Implementation for a {link: SessoinRepository}
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

    @Override
    public Session updateSession(Session session) {
        final org.hibernate.Session hibernateSession = em.unwrap(org.hibernate.Session.class);
        hibernateSession.update(session);
        return session;
    }
}
