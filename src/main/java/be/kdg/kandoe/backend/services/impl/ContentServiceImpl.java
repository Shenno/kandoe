package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.services.api.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {
    @Override
    public void addTheme(String emailAdress, String name, String description, boolean isCommentaryAllowed, boolean isAddingAdmited, String organisation, List<String> tags) {

    }

    @Override
    public Theme getTheme(String name) {
        return null;
    }
}
