package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;

import java.util.List;


/**
 * Extra interface to support customization of Spring's Data interfaces for {link: ThemeRepository}
 */
public interface ThemeRepositoryCustom {
    Theme addTheme(Theme theme) throws ContentServiceException;
    Theme updateTheme(Theme theme);

    List<Theme> getThemesByOrganisation(Organisation organisation);
    List<Theme> getThemesByOrganisatorId(int id);
}
