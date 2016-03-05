package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.SessionService;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sessions")
public class SessionRestController {

    private SessionService sessionService;
    MapperFacade mapperFacade;

    @Autowired
    public SessionRestController(SessionService sessionService, MapperFacade mapperFacade)
    {
        this.sessionService = sessionService;
        this.mapperFacade = mapperFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ThemeResource> createSession()
    {
        //Do magic
        return null;
    }
}
