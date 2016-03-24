package be.kdg.kandoe.frontend.controllers.resources.users;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * OrganisationResource is a Resource object for {link: Organisation}
 */
public class OrganisationResource implements Serializable {
    private Integer id;
    private String name;
    private List<ThemeResource> themes;
    private Integer organisatorId;
    private String organisatorName;
    private String errorMessage;

    public OrganisationResource() {
        this.themes = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ThemeResource> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeResource> themes) {
        this.themes = themes;
    }

    public Integer getOrganisatorId() {
        return organisatorId;
    }

    public void setOrganisatorId(Integer organisatorId) {
        this.organisatorId = organisatorId;
    }

    public String getOrganisatorName() {
        return organisatorName;
    }

    public void setOrganisatorName(String organisatorName) {
        this.organisatorName = organisatorName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
