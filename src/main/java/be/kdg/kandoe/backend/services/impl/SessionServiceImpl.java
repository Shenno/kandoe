
package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.CardSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.persistence.api.CardSessionRepository;
import be.kdg.kandoe.backend.persistence.api.SessionRepository;
import be.kdg.kandoe.backend.persistence.api.ThemeRepository;
import be.kdg.kandoe.backend.persistence.api.UserRepository;
import be.kdg.kandoe.backend.services.api.SessionService;
import be.kdg.kandoe.backend.services.exceptions.SessionServiceException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by   Shenno Willaert
 * Date         28/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.services.impl
 */

@Service("sessionService")
@Transactional
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final CardSessionRepository cardSessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, ThemeRepository themeRepository,
                              UserRepository userRepository, CardSessionRepository cardSessionRepository) {
        this.sessionRepository = sessionRepository;
        this.themeRepository = themeRepository;
        this.userRepository = userRepository;
        this.cardSessionRepository = cardSessionRepository;
    }

    @Override
    public Session addSession(Session session, int themeId) {
        Theme theme = this.themeRepository.findOne(themeId);
        session.setTheme(theme);
        return this.sessionRepository.save(session);
    }

    @Override
    public Session findSession(int sessionId) {
        Session session = this.sessionRepository.findOne(sessionId);
        Hibernate.initialize(session.getUsers());
        Hibernate.initialize(session.getCardSessions());
        return session;
    }

    @Override
    public Session addCardToSession(Session session, Card card) {
        CardSession cardsession = new CardSession(0, card.getText(), card.getImageURL(), session);
        session.addCardSession(cardsession);
        return sessionRepository.save(session);
    }

    @Override
    public Session addUserToSession(Session session, String username) throws SessionServiceException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new SessionServiceException("Can't add unexisting user to session");
        }
        session.addUser(user);
        return sessionRepository.save(session);
    }

    @Override
    public CardSession findCardSession(int cardSessionId) {
        return cardSessionRepository.findOne(cardSessionId);
    }

    @Override
    public void makeMove(CardSession cardSession, int userId) throws SessionServiceException {
        Session session = findSession(cardSession.getSession().getId());
        if(session.getCurrentUser() == userId) {
            cardSession.setPriority(cardSession.getPriority() + 1);
            session.updateCurrentUser();
            cardSessionRepository.save(cardSession);
            sessionRepository.save(session);
            return;
        }
        throw new SessionServiceException("It's not user " + userId + "'s turn");
    }
}

