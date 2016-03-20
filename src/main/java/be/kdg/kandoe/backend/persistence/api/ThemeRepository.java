package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Themerepository is an Interface for accessing a {link: Theme} in the Database
 */
public interface ThemeRepository extends JpaRepository<Theme, Integer>,JpaSpecificationExecutor<Theme>,ThemeRepositoryCustom {

}
