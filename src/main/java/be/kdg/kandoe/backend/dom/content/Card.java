package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Session;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Card for a {@link Theme}
 */

@Entity
@Table(name = "Card")
@NamedQueries(
        {
                @NamedQuery(name = "Card.findByText", query = "SELECT c FROM Card c WHERE c.text=:text"),
                @NamedQuery(name = "Card.findByThemeId", query = "SELECT c FROM Card c join c.theme t WHERE t.themeId=:themeId")
        })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Card implements Serializable, Identifiable<Integer> {

    @Column(name = "CardId", nullable = false)
    @Id
    @GeneratedValue
    private Integer cardId;

    @Column(name = "Text", nullable = false)
    private String text;

    @Column(name = "ImageURL", nullable = false)
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "ThemeId")
    private Theme theme;

    @OneToMany(targetEntity = Remark.class, mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Remark> remarks;

    public Card() {
    }
    public Card(String text, String imageURL, Theme theme) {
        this.text = text;
        this.imageURL = imageURL;
        this.theme= theme;
        this.remarks = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return cardId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
    }
}
