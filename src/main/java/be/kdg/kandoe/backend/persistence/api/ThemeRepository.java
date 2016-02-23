package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Evelien
 * @versionon 1.0 23-2-201611:12
 */
public interface ThemeRepository extends JpaRepository<Theme, Integer>,JpaSpecificationExecutor<Theme>,ThemeRepositoryCustom {

}
