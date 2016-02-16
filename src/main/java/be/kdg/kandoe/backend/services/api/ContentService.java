package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.content.Theme;

import java.util.List;

public interface ContentService {
    void addTheme(String emailAdress, String name, String description, boolean isCommentaryAllowed, boolean isAddingAdmited, String organisation, List<String> tags);

    Theme getTheme(String name);
}
