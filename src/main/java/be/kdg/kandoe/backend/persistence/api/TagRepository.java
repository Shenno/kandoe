package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Tagrepository is an Interface for accessing a {link: Tag} in the Database
 */
public interface TagRepository extends JpaRepository<Tag, Integer>,JpaSpecificationExecutor<Tag>,TagRepositoryCustom {

}
