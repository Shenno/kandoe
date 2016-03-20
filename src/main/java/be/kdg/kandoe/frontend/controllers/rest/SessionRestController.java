package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.SessionService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.SessionServiceException;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.CardSessionResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.RemarkResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.SessionResourceActive;
import be.kdg.kandoe.frontend.controllers.resources.sessions.SessionResourcePost;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RestController for everything with Session
 */
@RestController
@RequestMapping("api/sessions")
public class SessionRestController {

    private SessionService sessionService;
    private ContentService contentService;
    MapperFacade mapperFacade;

    @Autowired
    public SessionRestController(SessionService sessionService, ContentService contentService, MapperFacade mapperFacade)
    {
        this.sessionService = sessionService;
        this.contentService = contentService;
        this.mapperFacade = mapperFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SessionResourceActive>> findThemesByOrganisatorId(@AuthenticationPrincipal User user) {
        List<Session> foundSessions = sessionService.findSessionByUserId(user.getId());
        List<SessionResourceActive> sessionResources = foundSessions.stream().map(s -> mapperFacade.map(s, SessionResourceActive.class)).collect(Collectors.toList());
        return new ResponseEntity<List<SessionResourceActive>>(sessionResources, HttpStatus.OK);
    }

    @RequestMapping(value="/{sessionId}/terminate", method = RequestMethod.POST)
    public HttpStatus terminateSession(@AuthenticationPrincipal User user, @PathVariable Integer sessionId) {
        Session session = sessionService.findSession(sessionId);
        if(session.getOrganisator() == user.getUserId()) {
            session.setGameOver(true);
            sessionService.updateSession(session);
            return HttpStatus.OK;
        }
        return HttpStatus.UNAUTHORIZED;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SessionResourceActive> createAsynchronousSession(@RequestBody SessionResourcePost sessionResourcePost, @AuthenticationPrincipal User user)
    {
        Session session = new AsynchronousSession(user.getUserId(), true, 60, sessionResourcePost.getAmountOfCircles(), sessionResourcePost.getNameSession());
        Session persistedSession = null;
        List<Card> cards = new ArrayList<>();
       // session.setOrganisator(user.getUserId());


        //TODO: cards meegeven in resource OF exception opvangen als cardId niet gevonden is
        for(Integer c : sessionResourcePost.getCardIds()) {
            cards.add(contentService.getCard(c));
        }

        try {
            persistedSession = sessionService.addSession(session, sessionResourcePost.getThemeId(), cards, sessionResourcePost.getParticipantsEmails());
        } catch (SessionServiceException sse) {
            //TODO: return resource errorMessage setten
            session.setTheme(contentService.getTheme(sessionResourcePost.getThemeId()));
            SessionResourceActive sessionResourceActive = mapperFacade.map(session, SessionResourceActive.class);
            sessionResourceActive.setErrorMessage(sse.getMessage());
            return new ResponseEntity<SessionResourceActive>(sessionResourceActive, HttpStatus.OK);
        }
        return new ResponseEntity<SessionResourceActive>(mapperFacade.map(persistedSession, SessionResourceActive.class), HttpStatus.OK);
        /*String nameSession = sessionResourcePost.getNameSession();
        Session session = new AsynchronousSession(true, 60, sessionResourcePost.getAmountOfCircles(),nameSession);
        session.setOrganisator(user.getUserId());
        Session persistedSession = sessionService.addSession(session, sessionResourcePost.getThemeId());
        for(String s : sessionResourcePost.getParticipantsEmails()) {
            persistedSession = sessionService.addUserToSession(persistedSession, s);
        }

        //Kan niet op deze manier..? Moet result session hebben

         //sessionResourcePost.getCardIds().forEach(c -> sessionService.addCardToSession(session, contentService.getCard(c)));
        for(Integer c : sessionResourcePost.getCardIds()) {
            persistedSession =sessionService.addCardToSession(persistedSession, contentService.getCard(c));
            System.out.println(c);
        }


        System.out.println(sessionService.findSession(persistedSession.getId()).getCardSessions());
        //Do magic
        return new ResponseEntity<Integer>(persistedSession.getId(), HttpStatus.OK);*/
    }

    @RequestMapping(value="/{sessionId}", method = RequestMethod.GET)
    public ResponseEntity<SessionResourceActive> findAsynchronousSession(@PathVariable int sessionId) {
        AsynchronousSession session = (AsynchronousSession) sessionService.findSession(sessionId);
        return new ResponseEntity<SessionResourceActive>(mapperFacade.map(session, SessionResourceActive.class), HttpStatus.OK);
    }

    @RequestMapping(value="/{sessionId}/remarks", method = RequestMethod.POST)
    public ResponseEntity<List<RemarkResource>> createRemark(@AuthenticationPrincipal User user, @RequestBody RemarkResource remarkResource, @PathVariable int sessionId) {
        System.out.println(user.getUsername());
        System.out.println(remarkResource.getText());
        Session session = sessionService.findSession(sessionId);
        session = sessionService.addRemarkToSession(session, user.getUsername(), remarkResource.getText());
        List<RemarkResource> remarkResourceList = session.getRemarks().stream().map(r -> mapperFacade.map(r, RemarkResource.class)).collect(Collectors.toList());
        remarkResourceList.forEach(r -> r.setUsername(user.getUsername()));
        return new ResponseEntity<List<RemarkResource>>(remarkResourceList, HttpStatus.OK);
    }

    @RequestMapping(value="/{sessionId}", method = RequestMethod.POST)
    public ResponseEntity<SessionResourceActive> updateAsynchronousSession(@PathVariable int sessionId, @RequestBody CardSessionResource cardSessionResource, @AuthenticationPrincipal User user) {
        Session session = sessionService.findSession(sessionId);
        System.out.println(session.getCurrentUser());
        sessionService.makeMove(sessionService.findCardSession(cardSessionResource.getId()), user.getUserId());
        System.out.println(user.getUserId());
        session = sessionService.findSession(sessionId);
        System.out.println(session.getCurrentUser());
        return new ResponseEntity<SessionResourceActive>(mapperFacade.map(session, SessionResourceActive.class), HttpStatus.OK);
    }
}
