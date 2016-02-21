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
    public void addTheme(Integer userId, Theme theme) throws ContentServiceException{

        throw new ContentServiceException("Wrong arguments for theme");
    }

    @Override
    public Theme getTheme(String name) {
        return null;
    }

    @Override
    public Tag addTag(String name, int themeId) throws ContentServiceException{
        if(name.isEmpty()) {
            throw new ContentServiceException("Empty name for tag");
        } else if (themeId == 0) {
            throw new ContentServiceException("Empty theme");
        }
        return null;
    }

    @Override
    public Tag getTag(int tagId, int themeId) {
        return null;
        //return tagRepository.;
    }
}
