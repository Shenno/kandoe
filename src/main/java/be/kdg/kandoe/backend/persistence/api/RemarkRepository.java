package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Remark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Evelien
 * @versionon 1.0 29-2-201609:43
 */
public interface RemarkRepository extends JpaRepository<Remark, Integer>,JpaSpecificationExecutor<Remark>,RemarkRepositoryCustom {
}
