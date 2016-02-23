package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.persistence.api.CardRepository;
import be.kdg.kandoe.backend.persistence.api.TagRepository;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {

    private final TagRepository tagRepository;
    private final CardRepository cardRepository;

    @Autowired
    public ContentServiceImpl(TagRepository tagRepository, CardRepository cardRepository) {
        this.tagRepository = tagRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public Theme addTheme(Integer userId, Theme theme) throws ContentServiceException {
        if (userId == null) {
            throw new ContentServiceException("UserId may not be empty");
        } else if (theme == null) {
            throw new ContentServiceException("Theme may not be empty");
        } else {
            return null;
        }
    }

    @Override
    public Theme getTheme(int themeId) {
        return null;
    }

    @Override
    public Tag addTag(int themeId, Tag tag) throws ContentServiceException {
        if (tag == null) {
            throw new ContentServiceException("Tag can not be empty");
        } else if (tag.getTagName().isEmpty()) {
            throw new ContentServiceException("Empty name for tag");
        } else if (themeId == 0) {
            throw new ContentServiceException("Empty theme");
        }

        return tagRepository.addTag(tag);
    }

    @Override
    public Tag getTag(int tagId) {
        return tagRepository.findOne(tagId);
    }

    @Override
    public Card addCard(int themeId, Card card) {

        if (card == null) {
            throw new ContentServiceException("Card can not be empty");
        } else if (card.getText().isEmpty()) {
            throw new ContentServiceException("Empty Description");
        } else if (themeId == 0) {
            throw new ContentServiceException("Empty theme");
        }

        // Create a Pattern object
        Pattern r = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");

        // Now create matcher object.
        Matcher m = r.matcher(card.getImageURL());


        if (!m.find()) {
            throw new ContentServiceException("Wrong Image location");
        }


        return cardRepository.save(card);
    }

}
