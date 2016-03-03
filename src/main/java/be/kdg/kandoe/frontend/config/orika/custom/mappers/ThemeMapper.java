package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper extends CustomMapper<Theme, ThemeResource> {

    private UserService userService;

    @Autowired
    public ThemeMapper(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void mapAtoB(Theme theme, ThemeResource themeResource, MappingContext context) {
      //  mapperFacade.map(theme.getOrganisation(), themeResource.getOrganisation());
        // mapperFacade.map(organisation.getName(), organisationResource.getOrganisationName());
        themeResource.setOrganisationId(theme.getOrganisation().getId());
        if (theme.getOrganisators().size() == 0) themeResource.setOrganisatorId(1); //TODO: Slechts tijdelijk, zolang het default thema nog geen organisator heeft
        else themeResource.setOrganisatorId(theme.getOrganisators().get(0).getId());
    }

    @Override
    public void mapBtoA(ThemeResource themeResource, Theme theme, MappingContext context) {
     //   mapperFacade.map(themeResource.getOrganisation(), theme.getOrganisation());
        Organisation organisation = userService.getOrganisationById(themeResource.getOrganisationId());
        theme.setOrganisation(organisation);

        int organisatorId = themeResource.getOrganisatorId();
        if(organisatorId == 0) organisatorId = 1; //TODO: Slechts tijdelijk, zolang het default thema nog geen organisator heeft
        User organisator = userService.findUserById(organisatorId);
        theme.addOrganisator(organisator);

        super.mapBtoA(themeResource, theme, context);
    }
}