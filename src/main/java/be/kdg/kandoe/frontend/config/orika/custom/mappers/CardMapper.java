package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.frontend.controllers.resources.content.CardResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by Kevin on 28/02/2016.
 */
@Component
public class CardMapper extends CustomMapper<Card, CardResource> {

    @Override
    public void mapAtoB(Card card, CardResource cardResource, MappingContext context) {
        mapperFacade.map(card.getTheme(), cardResource.getThemeResource());
    }

    @Override
    public void mapBtoA(CardResource cardResource, Card card, MappingContext context) {
        super.mapBtoA(cardResource, card, context);
    }
}
