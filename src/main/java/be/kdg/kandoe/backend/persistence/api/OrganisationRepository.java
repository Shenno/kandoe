package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.user.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Organisaitionrepository is an Interface for accessing a {link: Organisation} in the Database
 */
public interface OrganisationRepository extends JpaRepository<Organisation, Integer>, JpaSpecificationExecutor<Organisation>, OrganisationRepositoryCustom {
}
