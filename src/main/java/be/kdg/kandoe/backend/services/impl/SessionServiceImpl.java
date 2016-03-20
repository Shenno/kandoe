
package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.CardSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.persistence.api.CardSessionRepository;
import be.kdg.kandoe.backend.persistence.api.SessionRepository;
import be.kdg.kandoe.backend.persistence.api.ThemeRepository;
import be.kdg.kandoe.backend.persistence.api.UserRepository;
import be.kdg.kandoe.backend.services.api.SessionService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import be.kdg.kandoe.backend.services.exceptions.SessionServiceException;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.hibernate.Hibernate;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * An implementation for {link: SessionService}
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

    /* Session */
    @Override
    public Session addSession(Session session, int themeId, List<Card> cards, List<String> usernames) throws SessionServiceException {
        Theme theme = this.themeRepository.findOne(themeId);
        if (theme == null) {
            throw new SessionServiceException("Ongeldig thema.");
        }
        boolean found = false;
        for(User u : theme.getOrganisators()) {
            if(u.getUserId() == session.getOrganisator()) {
                found = true;
            }
        }
        if(!found) {
            throw new SessionServiceException("Je kan geen sessie starten van een thema waar je geen organisator van bent");
        }
        session.setTheme(theme);
        if (session.getNameSession() == null || session.getNameSession().isEmpty()){
            throw new SessionServiceException("De sessie moet een naam hebben.");
        }
        if (session.getAmountOfCircles() < 3 || session.getAmountOfCircles() > 8) {
            throw new SessionServiceException("Een Kandoecirkel moet minimum 3 en maximum 8 schillen hebben.");
        }
        if(cards == null) {
            throw new SessionServiceException("Een sessie moet kaarten bevatten.");
        }
        if (cards.size() < 2 || cards.size() > 24) {
            throw new SessionServiceException("Een sessie moet minimum 2 en maximum 24 kaarten bevatten.");
        }
        if(usernames == null) {
            throw new SessionServiceException("Een sessie moet deelnemers bevatten.");
        }
        if (usernames.size() < 2) {
            throw new SessionServiceException("Een sessie moet minimaal 2 deelnemers hebben.");
        }
        for(Card c : cards) {
            if(c.getTheme().getId() != themeId) {
                throw new SessionServiceException("Een kaart van een ander thema dan het thema van de sessie kan niet worden toegevoegd.");
            }
            CardSession cardSession = new CardSession(session.getAmountOfCircles()+1, c.getText(), c.getImageURL(), session);
            session.addCardSession(cardSession);
        }
        for(String username : usernames) {
            User user = null;
            user = userRepository.findUserByUsername(username);
            if(user == null) {
                throw new SessionServiceException("Deelnemer kon niet toegevoegd worden aan de sessie");
            }
            session.addUser(user);
        }
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
        CardSession cardsession = new CardSession(session.getAmountOfCircles()+1, card.getText(), card.getImageURL(), session);
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
    public Session updateSession(Session session) {
        return sessionRepository.updateSession(session);
    }

    @Override
    public Session addRemarkToSession(Session session, String username, String text) {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new SessionServiceException("Onbestaande gebruiker kan geen opmerkingen toevoegen.");
        }
        boolean isParticipant = false;
        for(User participant : session.getUsers()) {
            if((participant.getUserId().equals(user.getUserId())) || (user.getUserId().equals(session.getOrganisator()))) {
                isParticipant = true;
            }
        }
        if(!isParticipant) {
            throw new SessionServiceException("Gebruiker die niet deelneemt aan de sessie of geen organisator is van de sessie mag geen opmerkingen plaatsen.");
        }
        Remark r = new Remark(text, user, session);
        session.addRemark(r);
        return sessionRepository.save(session);
    }

    @Override
    public Session makeMove(CardSession cardSession, int userId) throws SessionServiceException {
        Session session = findSession(cardSession.getSession().getId());
        if(session.isGameOver()) {
            throw new SessionServiceException("Zet kan niet uitgevoerd worden omdat de sessie gedaan is.");
        }
        if(cardSession.getDistanceToCenter() == 0) {
            throw new SessionServiceException("Deze zet kan niet uitgevoerd worden. Kaart bevindt zich al in het centrum van de cirkel.");
        }
        if(userRepository.findOne(userId) == null) {
            throw new SessionServiceException("Deze zet kan niet uitgevoerd worden omdat de gebruiker niet bestaat.");
        }
        if(session.getCurrentUser() == userId) {
            cardSession.setDistanceToCenter(cardSession.getDistanceToCenter() - 1);
            session.updateCurrentUser();
            if(cardSession.getDistanceToCenter() == 0) {
                session.setGameOver(true);
            }
            cardSessionRepository.save(cardSession);
            return sessionRepository.save(session);
        }
        if(session.getCurrentUser() != userId) {
            throw new SessionServiceException("Deze zet kan niet uitgevoerd worden omdat de gebruiker niet aan beurt is.");
        }
        return null;
    }

    @Override
    public List<Session> findSessionByUserId(int userId) throws SessionServiceException {
        User user = userRepository.getOne(userId);
        if (user == null){
            throw new SessionServiceException("User doesn't exist");
        }
        return sessionRepository.findSessionByUserId(userId);
    }
    @Override
    public CardSession findCardSession(int cardSessionId) {
        return cardSessionRepository.findOne(cardSessionId);
    }


}

