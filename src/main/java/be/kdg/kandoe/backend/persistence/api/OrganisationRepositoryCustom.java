package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.user.Organisation;

import java.util.List;

/**
 * Created by   Shenno Willaert
 * Date         22/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.persistence.api
 */
public interface OrganisationRepositoryCustom
{
    List<Organisation> getOrganisationByName(String name);
}
