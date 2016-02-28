package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;

import java.util.List;

public interface ContentService {
    /*Theme*/
    Theme addTheme(Theme theme);

    Theme getTheme(int themeId);

    List<Theme> findThemes();

    List<Theme> findThemesByOrganisation(Organisation organisation);

    void deleteTheme(int themeId);

    /*Tag*/
    Tag addTag(Tag tag);

    Tag getTag(int tagId);

    /*Card*/
    Card addCard (Card card);

    Card getCard(int cardId);

}
