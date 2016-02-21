package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.content.Tag;
import be.kdg.kandoe.backend.dom.content.Theme;

import java.util.List;

public interface ContentService {
    void addTheme(Integer userId, Theme theme);

    Theme getTheme(String name);

    Tag addTag(String name, int themeId);

    Tag getTag(int tagId, int themeId);
}
