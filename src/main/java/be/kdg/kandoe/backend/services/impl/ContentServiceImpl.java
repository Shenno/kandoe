package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.persistence.api.CardRepository;
import be.kdg.kandoe.backend.persistence.api.RemarkRepository;
import be.kdg.kandoe.backend.persistence.api.TagRepository;
import be.kdg.kandoe.backend.persistence.api.ThemeRepository;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {

    private final TagRepository tagRepository;
    private final CardRepository cardRepository;
    private final ThemeRepository themeRepository;
    private final RemarkRepository remarkRepository;

    @Autowired
    public ContentServiceImpl(TagRepository tagRepository, CardRepository cardRepository, ThemeRepository themeRepository, RemarkRepository remarkRepository) {
        this.tagRepository = tagRepository;
        this.cardRepository = cardRepository;
        this.themeRepository = themeRepository;
        this.remarkRepository = remarkRepository;
    }

    /*Theme*/
    @Override
    public Theme addTheme(Theme theme) throws ContentServiceException {
        if (theme == null) {
            throw new ContentServiceException("Theme can not be empty");
        } else if (theme.getThemeName().isEmpty()) {
            throw new ContentServiceException("Empty name for theme");
            //TODO: Moet nog terug uit commentaar wanneer een organisatie en organisatoren worden meegegeven
        } /*else if (theme.getOrganisation() == null) {
            throw new ContentServiceException("Organisation can not be empty");
        } else if (theme.getOrganisators().size() == 0) {
            throw new ContentServiceException("There must be at least one organisator");
        } */
        return themeRepository.addTheme(theme);
    }

    @Override
    public Theme getTheme(int themeId) {
        Theme foundTheme = themeRepository.findOne(themeId);
        return foundTheme;
    }

    @Override
    public List<Theme> findThemes() {
        return themeRepository.findAll();
    }

    @Override
    public List<Theme> findThemesByOrganisation(Organisation organisation) {
        return themeRepository.getThemesByOrganisation(organisation);
    }

    @Override
    public void deleteTheme(int themeId) {
        themeRepository.delete(themeId);
    }

    @Override
    public Theme updateTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    /*Tag*/
    @Override
    public Tag addTag(Tag tag) throws ContentServiceException {
        if (tag == null) {
            throw new ContentServiceException("Tag can not be empty");
        } else if (tag.getTagName().isEmpty()) {
            throw new ContentServiceException("Empty name for tag");
        } else if (tag.getTheme()==null) {
            throw new ContentServiceException("Empty theme");
        }

        return tagRepository.addTag(tag);
    }

    @Override
    public Tag getTag(int tagId) {
        return tagRepository.findOne(tagId);
    }

    @Override
    public List<Tag> findTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> findTagByTheme(Theme theme) {
        return tagRepository.findTagByTheme(theme);
    }

    @Override
    public Tag updateTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /*Card*/
    @Override
    public Card addCard(Card card) {

        if (card == null) {
            throw new ContentServiceException("Card can not be empty");
        } else if (card.getText().isEmpty()) {
            throw new ContentServiceException("Empty Description");
        } else if (card.getTheme() == null) {
            throw new ContentServiceException("Empty themeID");
        }


        // Create a Pattern object
        Pattern r = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");

        // Now create matcher object.
        Matcher m = r.matcher(card.getImageURL());


        if (!m.find()) {
            throw new ContentServiceException("Wrong Image location");
        }


        return cardRepository.addCard(card);
    }

    @Override
    public Card addCardWithTheme(Card card, int themeId) throws ContentServiceException {
        if(card.getText().isEmpty()) {
            throw new ContentServiceException("Empty text");
        }

        Theme theme = getTheme(themeId);
        if(theme == null) {
            throw new ContentServiceException("Theme is null");
        }

        List<Card> themeCards = theme.getCards();
        themeCards.add(card);
        theme.setCards(themeCards);

        theme = themeRepository.save(theme);
        card = cardRepository.save(card);

        return card;
    }

    @Override
    public Card getCard(int cardId) { return cardRepository.findOne(cardId); }

    /*Remark*/
    @Override
    public Remark addRemark(Remark remark) throws ContentServiceException{
        if (remark == null)
            throw new ContentServiceException("remark can not be empty");
        else if (remark.getText().isEmpty())
            throw new ContentServiceException("Empty text");
        else if (remark.getCard() == null)
            throw new ContentServiceException("Empty card");
        else if (remark.getUser() == null)
            throw new ContentServiceException("Empty user");
        return remarkRepository.addRemark(remark);
    }
}
