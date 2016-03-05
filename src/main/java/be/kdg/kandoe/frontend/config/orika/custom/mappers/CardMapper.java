package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.content.CardResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Kevin on 28/02/2016.
 */
@Component
public class CardMapper extends CustomMapper<Card, CardResource> {

    private ContentService contentService;

    @Autowired
    public CardMapper(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public void mapAtoB(Card card, CardResource cardResource, MappingContext context) {
        cardResource.setThemeId(card.getTheme().getId());
        super.mapAtoB(card, cardResource, context);
        //mapperFacade.map(card.getTheme(), cardResource.getThemeResource());
    }

    @Override
    public void mapBtoA(CardResource cardResource, Card card, MappingContext context) {
        card.setTheme(contentService.getTheme(cardResource.getThemeId()));
        super.mapBtoA(cardResource, card, context);
    }
}
