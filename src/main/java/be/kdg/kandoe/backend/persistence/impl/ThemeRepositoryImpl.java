package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.persistence.api.ThemeRepositoryCustom;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author: Evelien
 * @versionon 1.0 23-2-201611:14
 */
@Repository("themeRepository")
public class ThemeRepositoryImpl implements ThemeRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Theme addTheme(Theme theme) throws ContentServiceException {
        final TypedQuery<Theme> q = em.createNamedQuery("Theme.findByThemeNameByOrganisation", Theme.class);
        q.setParameter("themename", theme.getThemeName());
        q.setParameter("organisation",theme.getOrganisation());
        if (!q.getResultList().isEmpty())
        {
            throw new ContentServiceException("Theme " + theme.getThemeName() + " already exists in organisation "+theme.getOrganisation());
        }
        final Session session = em.unwrap(Session.class);
        session.saveOrUpdate(theme);
        return theme;
    }

    @Override
    public List<Theme> getThemesByOrganisation(Organisation organisation) {
        final TypedQuery<Theme> q = em.createNamedQuery("Theme.findThemesByOrganisation",Theme.class);
        q.setParameter("organisation",organisation);
        return q.getResultList();

    }
}
