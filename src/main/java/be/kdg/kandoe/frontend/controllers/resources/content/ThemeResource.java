package be.kdg.kandoe.frontend.controllers.resources.content;

import be.kdg.kandoe.backend.dom.content.Theme;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * Created by Kevin on 26/02/2016.
 */
public class ThemeResource extends ResourceSupport implements Serializable {

    private String description;
    private String name;
    private boolean isCommentaryAllowed;
    private boolean isAddingAdmitted;
    private int themeId;

    public ThemeResource() {}

    public ThemeResource(Theme theme) {
        this.description = theme.getDescription();
        this.name = theme.getThemeName();
        this.isCommentaryAllowed = theme.isCommentaryAllowed();
        this.isAddingAdmitted = theme.isAddingAdmited();
        this.themeId = theme.getId();
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCommentaryAllowed() {
        return isCommentaryAllowed;
    }

    public void setIsCommentaryAllowed(boolean isCommentaryAllowed) {
        this.isCommentaryAllowed = isCommentaryAllowed;
    }

    public boolean isAddingAdmitted() {
        return isAddingAdmitted;
    }

    public void setIsAddingAdmitted(boolean isAddingAdmitted) {
        this.isAddingAdmitted = isAddingAdmitted;
    }
}
