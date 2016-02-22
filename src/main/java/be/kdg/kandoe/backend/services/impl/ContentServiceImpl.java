package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.persistence.api.TagRepository;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {

    private final TagRepository tagRepository;

    @Autowired
    public ContentServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Theme addTheme(Integer userId, Theme theme) throws ContentServiceException{
        if(userId == null) {
            throw new ContentServiceException("UserId may not be empty");
        }
        else if(theme == null) {
            throw new ContentServiceException("Theme may not be empty");
        }
        else {
            return null;
        }
    }

    @Override
    public Theme getTheme(int themeId) {
        return null;
    }

    @Override
    public Tag addTag(int themeId,Tag tag) throws ContentServiceException{
        if (tag == null){
            throw new ContentServiceException("Tag can not be empty");
        } else if(tag.getName().isEmpty()) {
            throw new ContentServiceException("Empty name for tag");
        } else if (themeId == 0) {
            throw new ContentServiceException("Empty theme");
        }

        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(int tagId) {
        return null;
        //return tagRepository.;
    }
}
