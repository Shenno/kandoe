package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.persistence.api.ThemeRepositoryCustom;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
        validateTheme(theme);

        final Session session = em.unwrap(Session.class);
        session.save(theme);
        return theme;
    }

    private void validateTheme(Theme theme) {
        final TypedQuery<Theme> q = em.createNamedQuery("Theme.findByThemeNameByOrganisation", Theme.class);
        q.setParameter("themename", theme.getThemeName());
        q.setParameter("organisation",theme.getOrganisation());
        if (!q.getResultList().isEmpty())
        {
            throw new ContentServiceException("Een thema met de naam '" + theme.getThemeName() + "' is reeds aangemaakt voor organisatie '"+theme.getOrganisation().getName() + "'.");
        }
    }

    @Override
    public Theme updateTheme(Theme theme) {
        validateTheme(theme);

        final Session session = em.unwrap(Session.class);
        session.update(theme);
        return theme;
    }

    @Override
    public List<Theme> getThemesByOrganisation(Organisation organisation) {
        final TypedQuery<Theme> q = em.createNamedQuery("Theme.findThemesByOrganisation",Theme.class);
        q.setParameter("organisation",organisation);
        return q.getResultList();
    }

    @Override
    public List<Theme> getThemesByOrganisatorId(int organisatorId) {
        final TypedQuery<Theme> q = em.createNamedQuery("Theme.findThemesByOrganisatorId",Theme.class);
        //final javax.persistence.Query q = em.createQuery("SELECT t.themeId FROM Theme t join t.organisators o WHERE o.userId = :organisatorId");
        q.setParameter("organisatorId", organisatorId);
        //List<Integer> idList = (List<Integer>) q.getResultList();
        return q.getResultList();
    }
}
