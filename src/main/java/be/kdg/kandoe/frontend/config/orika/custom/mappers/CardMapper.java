package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.content.CardResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CardMapper is a Mapper to map {link: Card} to {link: CardResource} or in the other direction
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
    }

    @Override
    public void mapBtoA(CardResource cardResource, Card card, MappingContext context) {
        Theme theme = contentService.getTheme(cardResource.getThemeId());
        card.setTheme(theme);
        super.mapBtoA(cardResource, card, context);
    }
}
