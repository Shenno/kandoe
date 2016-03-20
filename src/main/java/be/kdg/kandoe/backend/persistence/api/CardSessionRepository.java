package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.session.CardSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * CardSessionrepository is an Interface for accessing a {link: CardSession} in the Database
 */
public interface CardSessionRepository extends JpaRepository<CardSession, Integer>, JpaSpecificationExecutor<CardSession> {
}
