package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.persistence.api.OrganisationRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by   Shenno Willaert
 * Date         22/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.persistence.impl
 */
@Repository("organisationRepository")
public class OrganisationRepositoryImpl implements OrganisationRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Organisation> getOrganisationByName(String name) {
        final TypedQuery<Organisation> q = em.createNamedQuery("Organisation.findOrganisationByName", Organisation.class);
        q.setParameter("name", name);
        return q.getResultList();
    }
}
