package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.user.Organisation;

/**
 * Created by   Shenno Willaert
 * Date         22/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.services.api
 */
public interface OrganisationService {
    Organisation addOrganisation(Organisation organisation);
    Organisation getOrganisationById(int id);
    Organisation getOrganisationByName(String name);
}
