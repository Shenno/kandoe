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
    private boolean picked;

    private ThemeResource themeResource;

    public CardResource() {
        this.themeResource = new ThemeResource();
    }

   /* public CardResource(Card card) {
        this.cardId = card.getId();
        this.text = card.getText();
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ThemeResource getThemeResource() {
        return themeResource;
    }

    public void setThemeResource(ThemeResource themeResource) {
        this.themeResource = themeResource;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }
}
