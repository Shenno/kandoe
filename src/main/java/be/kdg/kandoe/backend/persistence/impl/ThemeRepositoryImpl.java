package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.persistence.api.ThemeRepositoryCustom;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
        final TypedQuery<Theme> q = em.createNamedQuery("Theme.findByThemeName", Theme.class);
        q.setParameter("themename", theme.getThemeName());
        if (!q.getResultList().isEmpty())
        {
            throw new ContentServiceException("Theme " + theme.getThemeName() + " already exists");
        }
        final Session session = em.unwrap(Session.class);
        session.saveOrUpdate(theme);
        return theme;
    }
}
