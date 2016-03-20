package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;


/**
 *  Extra interface to support customization of Spring's Data interfaces for {link: RemarkRepository}
 */
public interface RemarkRepositoryCustom {
    Remark addRemarkToCard(Remark remark) throws ContentServiceException;
}
