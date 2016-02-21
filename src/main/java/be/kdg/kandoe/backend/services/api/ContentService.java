package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;

import java.util.List;

public interface ContentService {
    Theme addTheme(Integer userId, Theme theme);

    Theme getTheme(int themeId);

    Tag addTag(int themeId,Tag tag);

    Tag getTag(int tagId);
}
