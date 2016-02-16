package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;

import java.util.List;
import java.util.zip.ZipFile;

/**
 * Created by Len on 10-2-2016.
 */
public class Theme {

    private String name;
    private String description;
    private boolean isCommentaryAllowed;
    private boolean isAddingAdmited;
    private User organisator;
    private Organisation organisation;
    private List<Tag> tags;

    public Theme(String name, String description, boolean isCommentaryAllowed, boolean isAddingAdmited) {
        this.name = name;
        this.description = description;
        this.isCommentaryAllowed = isCommentaryAllowed;
        this.isAddingAdmited = isAddingAdmited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCommentaryAllowed() {
        return isCommentaryAllowed;
    }

    public void setIsCommentaryAllowed(boolean isCommentaryAllowed) {
        this.isCommentaryAllowed = isCommentaryAllowed;
    }

    public boolean isAddingAdmited() {
        return isAddingAdmited;
    }

    public void setIsAddingAdmited(boolean isAddingAdmited) {
        this.isAddingAdmited = isAddingAdmited;
    }

    public User getOrganisator() {
        return organisator;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
