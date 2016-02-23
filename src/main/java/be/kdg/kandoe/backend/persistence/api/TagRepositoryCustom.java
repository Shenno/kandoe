package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;

/**
 * @author: Evelien
 * @versionon 1.0 23-2-201609:16
 */
public interface TagRepositoryCustom {

    Tag addTag(Tag tag) throws ContentServiceException;
}
