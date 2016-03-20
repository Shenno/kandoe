package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.analysis.AprioriFrequentItemsetGenerator;
import be.kdg.kandoe.backend.analysis.FrequentItemsetData;
import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.CardSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.persistence.api.CardRepository;
import be.kdg.kandoe.backend.persistence.api.RemarkRepository;
import be.kdg.kandoe.backend.persistence.api.TagRepository;
import be.kdg.kandoe.backend.persistence.api.ThemeRepository;
import be.kdg.kandoe.backend.services.api.ContentService;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.ContentServiceException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation for {link: ContentService}
 */
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
        validateTheme(theme);
        theme.setTags(new ArrayList<>());
        theme = themeRepository.addTheme(theme);
        return theme;
    }

    private void validateTheme(Theme theme) throws ContentServiceException{
        if (theme == null) {
            throw new ContentServiceException("Thema mag niet leeg zijn");
        } else if (theme.getThemeName().isEmpty()) {
            throw new ContentServiceException("Themanaam mag niet leeg zijn");
        } else if (theme.getOrganisation() == null) {
            throw new ContentServiceException("Je moet een thema aanmaken voor een bestaande organisatie.");
        } else if (theme.getOrganisators().size() == 0) {
            throw new ContentServiceException("Er moet tenminste één organisator zijn");
        }
    }

    @Override
    public Theme getTheme(int themeId) throws ContentServiceException {
        Theme foundTheme = null;
        try {
            foundTheme = themeRepository.findOne(themeId);
            Hibernate.initialize(foundTheme.getTags());
            return foundTheme;
        }
        catch (Exception e) {
            throw new ContentServiceException("Onbestaand thema");
        }
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
    public List<Theme> findThemesByOrganisatorId(int organisatorId) {
        return themeRepository.getThemesByOrganisatorId(organisatorId);
    }

    @Override
    public void deleteTheme(int themeId) {
        themeRepository.delete(themeId);
    }

    @Override
    public Theme updateTheme(Theme theme) throws ContentServiceException {
        validateTheme(theme);
        theme.setTags(new ArrayList<>());
        return themeRepository.updateTheme(theme);
    }

    /*Tag*/
    @Override
    public Tag addTag(Tag tag) throws ContentServiceException {
        if (tag == null) {
            throw new ContentServiceException("Tag mag niet leeg zijn");
        } else if (tag.getTagName().isEmpty()) {
            throw new ContentServiceException("Lege naam voor tag");
        } else if (tag.getTheme()==null) {
            throw new ContentServiceException("Leeg thema");
        }

        tag.setTagName(tag.getTagName().toLowerCase());
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
    public Tag findTagByTagNameByTheme(String tagname, Theme theme) throws ContentServiceException{
        try {
            return tagRepository.findTagByTagNameByTheme(tagname, theme).get(0);
        } catch (IndexOutOfBoundsException ex) {
            throw new ContentServiceException("Tag '" + tagname + "' werd niet gevonden voor thema '" + theme.getThemeName() + "'");
        }
    }

    @Override
    public Tag updateTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /*Card*/

    @Override
    public List<Card> findCardsByThemeId(int themeId) {
        return cardRepository.getCardsByThemeId(themeId);
    }

    @Override
    public List<Set<CardSession>> findMostFrequentCardCombinations(Integer themeId) {
        Theme theme = getTheme(themeId);

        if (theme.getSessions().size() > 0) {

            AprioriFrequentItemsetGenerator<CardSession> generator = new AprioriFrequentItemsetGenerator<>();

            List<Set<CardSession>> itemsetList = new ArrayList<>();

            for (Session session : theme.getSessions()) {
                itemsetList.add(new HashSet<>(session.getCardSessions()));
            }

            FrequentItemsetData<CardSession> data = generator.generate(itemsetList, 0.0001);

            List<Set<CardSession>> frequentItemsetList = data.getFrequentItemsetList();

            return frequentItemsetList;
        }

        return null;
    }

    @Override
    public Card addCard(Card card) {

        if (card == null) {
            throw new ContentServiceException("Card can not be empty");
        } else if (card.getText().isEmpty()) {
            throw new ContentServiceException("Empty Description");
        }else if (card.getTheme() == null) {
            throw new ContentServiceException("Empty themeID");
        }else if (card.getImageURL().isEmpty()){
            card.setImageURL("http://1.bp.blogspot.com/-TTxz7Nt7es0/Uxf7CoQJRUI/AAAAAAAAHg4/3XrVdDOIxIE/s1600/dummy.gif");
        }

        Pattern r = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");
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
    public void deleteCard(int cardId) {
        cardRepository.delete(cardId);
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
        return remarkRepository.addRemarkToCard(remark);
    }
}
