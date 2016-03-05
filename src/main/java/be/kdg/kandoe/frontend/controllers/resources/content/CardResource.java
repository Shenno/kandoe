package be.kdg.kandoe.frontend.controllers.resources.content;

import be.kdg.kandoe.backend.dom.content.Card;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * Created by Kevin on 28/02/2016.
 */
public class CardResource  implements Serializable {
    private Integer id;
    private String text;
    private String imageURL;
    private Integer themeId;

    private ThemeResource themeResource;

    public CardResource() {
        this.themeResource = new ThemeResource();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public void setText(String text) {
        this.text = text;
    }

   /* public ThemeResource getThemeResource() {
        return themeResource;
    }

    public void setThemeResource(ThemeResource themeResource) {
        this.themeResource = themeResource;
    }*/


}
