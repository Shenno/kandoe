package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;

import java.util.List;

/**
 *  Extra interface to support customization of Spring's Data interfaces for {link: TagRepository}
 */
public interface TagRepositoryCustom {

    Tag addTag(Tag tag) throws ContentServiceException;

    List<Tag> findTagByTheme(Theme theme);

    List<Tag> findTagByTagNameByTheme(String tagname, Theme theme);
}
