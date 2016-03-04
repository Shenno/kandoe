package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by   Shenno Willaert
 * Date         3/03/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.persistence.api
 */
public interface SessionRepository extends JpaRepository<Session, Integer>, JpaSpecificationExecutor<Session> {
}
