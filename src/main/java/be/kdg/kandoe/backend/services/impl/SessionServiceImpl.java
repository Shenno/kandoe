package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.session.Participation;
import be.kdg.kandoe.backend.persistence.api.ParticipationRepository;
import be.kdg.kandoe.backend.services.api.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by   Shenno Willaert
 * Date         28/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.services.impl
 */
@Service("sessionService")
@Transactional
public class SessionServiceImpl implements SessionService {
    private final ParticipationRepository participationRepository;

    @Autowired
    public SessionServiceImpl(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    //Participations

    @Override
    public Participation addParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    @Override
    public Participation getParticipation(int pId) {
        return participationRepository.findOne(pId);
    }
}
