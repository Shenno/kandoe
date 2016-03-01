package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper extends CustomMapper<Theme, ThemeResource> {
    @Override
    public void mapAtoB(Theme theme, ThemeResource themeResource, MappingContext context) {
        mapperFacade.map(theme.getOrganisation(), themeResource.getOrganisation());
        // mapperFacade.map(organisation.getName(), organisationResource.getOrganisationName());
    }

    @Override
    public void mapBtoA(ThemeResource themeResource, Theme theme, MappingContext context) {
        mapperFacade.map(themeResource.getOrganisation(), theme.getOrganisation());
        System.out.println("test");
      //  super.mapBtoA(themeResource, theme, context);
    }
}
