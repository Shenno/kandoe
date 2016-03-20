package be.kdg.kandoe.frontend.controllers.resources.content;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.frontend.controllers.resources.users.OrganisationResource;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.List;

/**
 * ThemeResource is a Resource object for {link: Theme}
 */
public class ThemeResource implements Serializable {

    private String description;
    private String themeName;
    private boolean commentaryAllowed;
    private boolean addingAdmitted;
    private Integer themeId;
    private Integer organisationId;
    private Integer organisatorId;
    private List<String> tags;
    private List<String> organisatorNames;
    private String errorMessage;

    public ThemeResource() {}

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
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

    public void setCommentaryAllowed(boolean commentaryAllowed) {
        this.commentaryAllowed = commentaryAllowed;
    }

    public boolean isAddingAdmitted() {
        return addingAdmitted;
    }

    public void setAddingAdmitted(boolean addingAdmitted) {
        this.addingAdmitted = addingAdmitted;
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public Integer getOrganisatorId() {
        return organisatorId;
    }

    public void setOrganisatorId(Integer organisatorId) {
        this.organisatorId = organisatorId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getOrganisatorNames() {
        return organisatorNames;
    }

    public void setOrganisatorNames(List<String> organisatorNames) {
        this.organisatorNames = organisatorNames;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
