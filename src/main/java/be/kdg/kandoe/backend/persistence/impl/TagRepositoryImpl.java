package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.persistence.api.TagRepositoryCustom;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation for a {link: TagRepository}
 */
@Repository("tagRepository")
public class TagRepositoryImpl implements TagRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Tag addTag(Tag tag) throws ContentServiceException {
        List<Tag> tags = findTagByTagNameByTheme(tag.getTagName(), tag.getTheme());
        if (!tags.isEmpty())
        {
            throw new ContentServiceException("Tag " + tag.getTagName() + " already exists in Theme "+ tag.getTheme());
        }
        final Session session = em.unwrap(Session.class);
        session.save(tag);
        return tag;
    }

    @Override
    public List<Tag> findTagByTheme(Theme theme) {
        final TypedQuery<Tag> q = em.createNamedQuery("Tag.findTagByTheme",Tag.class);
        q.setParameter("theme",theme);
        return q.getResultList();
    }

    @Override
    public List<Tag> findTagByTagNameByTheme(String tagname, Theme theme) {
        final TypedQuery<Tag> q = em.createNamedQuery("Tag.findByTagNamebyTheme", Tag.class);
        q.setParameter("tagname", tagname);
        q.setParameter("theme", theme);
        return q.getResultList();
    }
}
