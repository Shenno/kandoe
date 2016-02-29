package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;

/**
 * @author: Evelien
 * @versionon 1.0 29-2-201609:44
 */
public interface RemarkRepositoryCustom {
    Remark addRemark(Remark remark) throws ContentServiceException;

}
