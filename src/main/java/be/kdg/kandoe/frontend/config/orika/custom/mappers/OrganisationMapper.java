package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.frontend.controllers.resources.users.OrganisationResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by Kevin on 28/02/2016.
 */
@Component
public class OrganisationMapper extends CustomMapper<Organisation, OrganisationResource> {
    @Override
    public void mapAtoB(Organisation organisation, OrganisationResource organisationResource, MappingContext context) {
        mapperFacade.map(organisation.getOrganisator(), organisationResource.getOrganisator());
        mapperFacade.map(organisation.getThemes(), organisationResource.getThemes());
    }

    @Override
    public void mapBtoA(OrganisationResource organisationResource, Organisation organisation, MappingContext context) {
        super.mapBtoA(organisationResource, organisation, context);
    }
}
