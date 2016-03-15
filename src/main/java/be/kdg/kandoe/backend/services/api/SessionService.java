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
 * Created by   Shenno Willaert
 * Date         28/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.services.api
 */
public interface SessionService {
    // Session
    Session addSession(Session session, int themeId);
    Session findSession(int sessionId);
    Session addCardToSession(Session session, Card card);
    Session addUserToSession(Session session, String username) throws SessionServiceException;
    Session addRemarkToSession(Session session, String username, String text);
    CardSession findCardSession(int cardSessionId);
    void makeMove(CardSession cardSession, int userId) throws SessionServiceException;
    List<Session> findSessionByUserId(int userId) throws SessionServiceException;
    Session updateSession(Session session);
}
