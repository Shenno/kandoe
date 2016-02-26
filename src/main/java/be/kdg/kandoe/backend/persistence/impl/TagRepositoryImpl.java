package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.persistence.api.TagRepositoryCustom;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author: Evelien
 * @versionon 1.0 23-2-201609:15
 */
@Repository("tagRepository")
public class TagRepositoryImpl implements TagRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Tag addTag(Tag tag) throws ContentServiceException {
        final TypedQuery<Tag> q = em.createNamedQuery("Tag.findByTagNamebyTheme", Tag.class);
        q.setParameter("tagname", tag.getTagName());
        q.setParameter("theme",tag.getTheme());
        if (!q.getResultList().isEmpty())
        {
            throw new ContentServiceException("Tag " + tag.getTagName() + " already exists in Theme "+ tag.getTheme());
        }
        final Session session = em.unwrap(Session.class);
        session.saveOrUpdate(tag);
        return tag;
    }
}
