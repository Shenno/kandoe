package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.frontend.controllers.resources.content.CardResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.CardSessionResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.SessionResourceActive;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kevin on 5/03/2016.
 */
@Component
public class SessionMapper extends CustomMapper<AsynchronousSession, SessionResourceActive> {
    @Override
    public void mapAtoB(AsynchronousSession asynchronousSession, SessionResourceActive sessionResourceActive, MappingContext context) {
        List<CardSessionResource> cardSessionResources = asynchronousSession.getCardSessions().stream().map(cs -> mapperFacade.map(cs, CardSessionResource.class)).collect(Collectors.toList());
        //mapperFacade.map(asynchronousSession.getCardSessions(), sessionResourceActive.getCardSessionResources());
        sessionResourceActive.setCardSessionResources(cardSessionResources);
    }

    @Override
    public void mapBtoA(SessionResourceActive sessionResourceActive, AsynchronousSession asynchronousSession, MappingContext context) {
        mapperFacade.map(sessionResourceActive.getCardSessionResources(), asynchronousSession.getCardSessions(), context);
    }
}
