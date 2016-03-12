package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;

import java.util.List;
import java.util.Set;

public interface ContentService {
    /*Theme*/
    Theme addTheme(Theme theme);

    Theme getTheme(int themeId);

    List<Theme> findThemesByOrganisatorId(int organisatorId);

    List<Theme> findThemes();

    List<Theme> findThemesByOrganisation(Organisation organisation);

    void deleteTheme(int themeId);

    /*Tag*/
    Tag addTag(Tag tag);

    Tag getTag(int tagId);

    /*Card*/
    Card addCard (Card card);

    List<Card> findCardsByThemeId(int themeId);

    List<Set<String>> findMostFrequentCardCombinations(Integer themeId);

    Card addCardWithTheme(Card card, int themeId);

    Card getCard(int cardId);

    void deleteCard(int cardId);

    List<Tag> findTags();

    List<Tag> findTagByTheme(Theme theme);

    Tag findTagByTagNameByTheme(String tagname, Theme theme);

    Theme updateTheme(Theme theme);

    Tag updateTag(Tag tag);

    Remark addRemark(Remark remark);
}
