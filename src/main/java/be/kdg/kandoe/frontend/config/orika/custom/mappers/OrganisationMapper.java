package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.users.OrganisationResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * OrganisationMapper is a Mapper to map {link: Organisation} to {link: OrganisationResource} or in the other direction
 */
@Component
public class OrganisationMapper extends CustomMapper<Organisation, OrganisationResource> {
    private UserService userService;

    @Autowired
    public OrganisationMapper(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void mapAtoB(Organisation organisation, OrganisationResource organisationResource, MappingContext context) {
        organisationResource.setOrganisatorId(organisation.getOrganisator().getId());
        organisationResource.setOrganisatorName((organisation.getOrganisator().getFirstName())+" "+(organisation.getOrganisator().getLastName()));
        List<String> themeNames = new ArrayList<>();
        for(Theme theme: organisation.getThemes()) {
            themeNames.add(theme.getThemeName());
        }
        organisationResource.setThemes(themeNames);
    }

    @Override
    public void mapBtoA(OrganisationResource organisationResource, Organisation organisation, MappingContext context) {
        int organisatorId = organisationResource.getOrganisatorId();
        User organisator = userService.findUserById(organisatorId);
        organisation.setOrganisator(organisator);
        super.mapBtoA(organisationResource, organisation, context);
    }
}
