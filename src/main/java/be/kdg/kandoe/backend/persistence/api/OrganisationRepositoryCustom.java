package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.user.Organisation;

import java.util.List;


/**
 * Extra interface to support customization of Spring's Data interfaces for {link: OrganisationRepository}
 */
public interface OrganisationRepositoryCustom
{
    List<Organisation> getOrganisationByName(String name);
}
