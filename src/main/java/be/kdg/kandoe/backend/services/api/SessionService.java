package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.CardSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.services.exceptions.SessionServiceException;

import java.util.List;

/**
 * A SessionService is an Interface for the following classes:
 * {link: Sessio}, {link: SynchronousSession}, {link: Asynchrounoussession}, {link: CardSession}, {link: EndingGameCondition}
 */
public interface SessionService {
    /* Session */
    Session addSession(Session session, int themeId, List<Card> cards, List<String> usernames);
    Session findSession(int sessionId);
    Session addCardToSession(Session session, Card card);
    Session addUserToSession(Session session, String username) throws SessionServiceException;
    Session addRemarkToSession(Session session, String username, String text);
    List<Session> findSessionByUserId(int userId) throws SessionServiceException;
    Session updateSession(Session session);
    Session makeMove(CardSession cardSession, int userId) throws SessionServiceException;
    /* Card Session */
    CardSession findCardSession(int cardSessionId);

}
