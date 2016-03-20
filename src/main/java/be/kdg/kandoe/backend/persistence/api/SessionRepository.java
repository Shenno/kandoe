package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Sessionrepository is an Interface for accessing a {link: Session} in the Database
 */
public interface SessionRepository extends JpaRepository<Session, Integer>, JpaSpecificationExecutor<Session>,SessionRepositoyCustom {
}
