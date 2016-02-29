package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.persistence.api.RemarkRepositoryCustom;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author: Evelien
 * @versionon 1.0 29-2-201609:45
 */
@Repository("remarkRepository")
public class RemarkRepositoryImpl implements RemarkRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Remark addRemark(Remark remark) throws ContentServiceException {
        final TypedQuery<Remark> q = em.createNamedQuery("Remark.findByTextByCard", Remark.class);
        q.setParameter("text", remark.getText());
        q.setParameter("card",remark.getCard());
        if (!q.getResultList().isEmpty())
        {
            throw new ContentServiceException("Remark " + remark.getText() + " already exists in card "+remark.getCard());
        }
        final Session session = em.unwrap(Session.class);
        session.saveOrUpdate(remark);
        return remark;
    }
}

