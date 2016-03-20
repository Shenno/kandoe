package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;

import java.util.List;

/**
 * Extra interface to support customization of Spring's Data interfaces for {link: SessionRepository}
 */
public interface SessionRepositoyCustom {

    List<Session> findSessionByUserId(int userId);
    Session updateSession(Session session);
}
