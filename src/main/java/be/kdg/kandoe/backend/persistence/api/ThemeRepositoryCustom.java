package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;

import java.util.List;

/**
 * @author: Evelien
 * @versionon 1.0 23-2-201611:13
 */
public interface ThemeRepositoryCustom {
    Theme addTheme(Theme theme) throws ContentServiceException;
    Theme updateTheme(Theme theme);

    List<Theme> getThemesByOrganisation(Organisation organisation);
}
