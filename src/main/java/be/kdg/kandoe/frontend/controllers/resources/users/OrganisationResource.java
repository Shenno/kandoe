package be.kdg.kandoe.frontend.controllers.resources.users;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by   Shenno Willaert
 * Date         26/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.frontend.controllers.resources.users
 */
public class OrganisationResource {
    private int organisationId;
    private String organisationName;
    private List<ThemeResource> themes;

    public OrganisationResource(Organisation organisation) {
        this.organisationId = organisation.getId();
        this.organisationName = organisation.getName();
        List<ThemeResource> themeResources = new ArrayList<>();
        for(Theme t : organisation.getThemes()) {
            themeResources.add(new ThemeResource(t));
        }
        this.setThemes(themeResources);
    }

    public OrganisationResource() {
        this.themes = new ArrayList<>();
    }

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public List<ThemeResource> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeResource> themes) {
        this.themes = themes;
    }
}
