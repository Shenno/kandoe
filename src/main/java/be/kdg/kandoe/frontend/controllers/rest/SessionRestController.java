package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.SessionService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.SessionResourceActive;
import be.kdg.kandoe.frontend.controllers.resources.sessions.SessionResourcePost;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.AbstractDocument;
import java.util.stream.Collectors;


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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integer> createAsynchronousSession(@RequestBody SessionResourcePost sessionResourcePost)
    {
        final AsynchronousSession session = new AsynchronousSession(true, 60, 4);

        Session persistedSession = sessionService.addSession(session, sessionResourcePost.getThemeId());
        sessionResourcePost.getParticipantsEmails().forEach(e -> sessionService.addUserToSession(session, e));

        //Kan niet op deze manier..? Moet result session hebben
         sessionResourcePost.getCardIds().forEach(c -> sessionService.addCardToSession(session, contentService.getCard(c)));

        //PUSH

        /*for(Integer c : sessionResourcePost.getCardIds()) {
            persistedSession = sessionService.addCardToSession(session, contentService.getCard(c));
        }*/

        //Do magic
        return new ResponseEntity<Integer>(persistedSession.getId(), HttpStatus.OK);
    }

    @RequestMapping(value="/{sessionId}", method = RequestMethod.GET)
    public ResponseEntity<SessionResourceActive> findAsynchronousSession(@PathVariable int sessionId) {
        AsynchronousSession session = (AsynchronousSession) sessionService.findSession(sessionId);
        return new ResponseEntity<SessionResourceActive>(mapperFacade.map(session, SessionResourceActive.class), HttpStatus.OK);
    }
}
