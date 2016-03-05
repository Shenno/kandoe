package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.SessionService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.SessionResourcePost;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.AbstractDocument;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/sessions")
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
    public ResponseEntity<Integer> createSession(@RequestBody SessionResourcePost sessionResourcePost)
    {
        final Session session = new AsynchronousSession(true, 60);

        Session persistedSession = sessionService.addSession(session, sessionResourcePost.getThemeId());
        sessionResourcePost.getParticipantsEmails().forEach(e -> sessionService.addUserToSession(session, e));
        sessionResourcePost.getCardsIds().forEach(c -> sessionService.addCardToSession(session, contentService.getCard(c)));

        //Do magic
        return new ResponseEntity<Integer>(persistedSession.getId(), HttpStatus.OK);
    }
}
