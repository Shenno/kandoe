package be.kdg.kandoe.frontend.controllers.resources.content;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.frontend.controllers.resources.users.OrganisationResource;
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
    private Integer themeId;
    private Integer organisationId;
    private Integer organisatorId;
//    private OrganisationResource organisation;

    public ThemeResource() {}

  /*  public ThemeResource(Theme theme) {
        this.description = theme.getDescription();
        this.name = theme.getThemeName();
        this.commentaryAllowed = theme.isCommentaryAllowed();
        this.addingAdmitted = theme.isAddingAdmitted();
        this.themeId = theme.getId();
    }*/

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

    /*public OrganisationResource getOrganisation() {
        return organisation;
    }

    public void setOrganisation(OrganisationResource organisationResource) {
        this.organisation = organisationResource;
    }*/

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
}
