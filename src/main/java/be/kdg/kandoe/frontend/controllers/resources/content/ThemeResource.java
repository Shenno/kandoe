package be.kdg.kandoe.frontend.controllers.resources.content;

import be.kdg.kandoe.backend.dom.content.Theme;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * Created by Kevin on 26/02/2016.
 */
public class ThemeResource implements Serializable {

    private String description;
    private String themeName;
    private boolean commentaryAllowed;
    private boolean addingAdmitted;
    private Integer id;

    public ThemeResource() {}

  /*  public ThemeResource(Theme theme) {
        this.description = theme.getDescription();
        this.name = theme.getThemeName();
        this.commentaryAllowed = theme.isCommentaryAllowed();
        this.addingAdmitted = theme.isAddingAdmited();
        this.themeId = theme.getId();
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public boolean isCommentaryAllowed() {
        return commentaryAllowed;
    }

    public void setIsCommentaryAllowed(boolean isCommentaryAllowed) {
        this.commentaryAllowed = isCommentaryAllowed;
    }

    public boolean isAddingAdmitted() {
        return addingAdmitted;
    }

    public void setIsAddingAdmitted(boolean isAddingAdmitted) {
        this.addingAdmitted = isAddingAdmitted;
    }
}
