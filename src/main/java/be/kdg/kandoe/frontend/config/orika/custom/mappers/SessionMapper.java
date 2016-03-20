package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.content.CardResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.CardSessionResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.RemarkResource;
import be.kdg.kandoe.frontend.controllers.resources.sessions.SessionResourceActive;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SessionMapper is a Mapper to map {link: Session} to {link: SessionResource} or in the other direction
 */
@Component
public class SessionMapper extends CustomMapper<Session, SessionResourceActive> {
    @Override
    public void mapAtoB(Session session, SessionResourceActive sessionResourceActive, MappingContext context) {
        sessionResourceActive.setAmountOfUsers(session.getUsers().size());
        sessionResourceActive.setThemeName(session.getTheme().getThemeName());
        //System.out.println("IN DE MAPPER" + asynchronousSession.getCardSessions().size());
        List<CardSessionResource> cardSessionResources = session.getCardSessions().stream().map(cs -> mapperFacade.map(cs, CardSessionResource.class)).collect(Collectors.toList());
        List<RemarkResource> remarkResources = session.getRemarks().stream().map(r -> mapperFacade.map(r, RemarkResource.class)).collect(Collectors.toList());
        //mapperFacade.map(asynchronousSession.getCardSessions(), sessionResourceActive.getCardSessionResources());
        sessionResourceActive.setCardSessionResources(cardSessionResources);
        sessionResourceActive.setRemarks(remarkResources);
        //super.mapAtoB(asynchronousSession, sessionResourceActive, context);
    }

    @Override
    public void mapBtoA(SessionResourceActive sessionResourceActive, Session session, MappingContext context) {
        mapperFacade.map(sessionResourceActive.getCardSessionResources(), session.getCardSessions(), context);
    }
}
