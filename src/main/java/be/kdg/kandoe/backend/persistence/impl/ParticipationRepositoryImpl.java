package be.kdg.kandoe.backend.persistence.impl;

//import be.kdg.kandoe.backend.persistence.api.ParticipationRepository;
import be.kdg.kandoe.backend.persistence.api.ParticipationRepositoryCustom;
import be.kdg.kandoe.backend.persistence.api.TagRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by   Shenno Willaert
 * Date         28/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.persistence.impl
 */
@Repository("participationRepository")
public class ParticipationRepositoryImpl implements ParticipationRepositoryCustom {

    @PersistenceContext
    private EntityManager em;


}
