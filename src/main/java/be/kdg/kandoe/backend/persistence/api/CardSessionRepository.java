package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.session.CardSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by   Shenno Willaert
 * Date         5/03/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.persistence.api
 */
public interface CardSessionRepository extends JpaRepository<CardSession, Integer>, JpaSpecificationExecutor<CardSession> {
}
