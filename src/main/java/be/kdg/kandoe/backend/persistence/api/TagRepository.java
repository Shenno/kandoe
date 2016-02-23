package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Devi Bral
 * @version 1.0 19/02/2016 13:41
 */
public interface TagRepository extends JpaRepository<Tag, Integer>,JpaSpecificationExecutor<Tag>,TagRepositoryCustom {

}
