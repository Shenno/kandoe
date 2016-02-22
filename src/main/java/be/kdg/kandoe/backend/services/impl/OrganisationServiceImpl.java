package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.persistence.api.OrganisationRepository;
import be.kdg.kandoe.backend.persistence.api.TagRepository;
import be.kdg.kandoe.backend.services.api.OrganisationService;
import be.kdg.kandoe.backend.services.exceptions.OrganisationServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by   Shenno Willaert
 * Date         22/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.services.impl
 */

@Service("organisationService")
@Transactional
public class OrganisationServiceImpl implements OrganisationService, Serializable {

    private final OrganisationRepository organisationRepository;

    @Autowired
    public OrganisationServiceImpl(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public Organisation addOrganisation(Organisation organisation) throws OrganisationServiceException {
        if(organisation.getName().isEmpty()) {
            throw new OrganisationServiceException("Empty name");
        }
        if(getOrganisationByName(organisation.getName()) != null) {
            throw new OrganisationServiceException("Duplicate name");
        }

        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation getOrganisationById(int id) {
        return organisationRepository.findOne(id);
    }

    @Override
    public Organisation getOrganisationByName(String name) {
        List<Organisation> organisation = organisationRepository.getOrganisationByName(name);
        if(organisation.size() == 0) {
            return null;
        }
        return organisationRepository.getOrganisationByName(name).get(0);
    }
}
