package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;

import java.util.List;

public interface ContentService {
    Theme addTheme(Theme theme);

    Theme getTheme(int themeId);

    Tag addTag(Tag tag);

    Tag getTag(int tagId);

    Card addCard (Card card);

    void deleteTheme(int themeId);
}
