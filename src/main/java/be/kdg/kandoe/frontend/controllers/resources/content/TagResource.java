package be.kdg.kandoe.frontend.controllers.resources.content;

import be.kdg.kandoe.backend.dom.content.Tag;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * TagResource is a Resource object for {link: Tag}
 */
public class TagResource extends ResourceSupport implements Serializable {

    private int tagId;
    private String tagName;
    private ThemeResource themeResource;
    private int themeId;

    public TagResource() {}

    public TagResource(Tag tag) {
        this.tagId = tag.getId();
        this.tagName = tag.getTagName();
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setThemeResource(ThemeResource themeResource) {
        this.themeResource = themeResource;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Tag toDOM() {
        return new Tag(this.tagName, null);
    }
}
