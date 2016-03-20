package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Remark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Remarkrepository is an Interface for accessing a {link: Remark} in the Database
 */
public interface RemarkRepository extends JpaRepository<Remark, Integer>,JpaSpecificationExecutor<Remark>,RemarkRepositoryCustom {
}
