package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.CardSession;
import be.kdg.kandoe.backend.dom.session.Session;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Card")
@NamedQueries(
        {
                @NamedQuery(name = "Card.findByText", query = "SELECT c FROM Card c WHERE c.text=:text"),
        })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Card implements Serializable, Identifiable<Integer> {

    public Card() {
    }

    @Column(name = "CardId", nullable = false)
    @Id
    @GeneratedValue
    private Integer cardId;

    //TODO nullables checken

    @Column(name = "Text", nullable = true)
    private String text;

    @Column(name = "ImageURL", nullable = true)
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "ThemeId")
    private Theme theme;

    @OneToMany(targetEntity = Remark.class, mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Remark> remarks; //TODO review

    public Card(String text, Theme theme) {
        this(text, "www.google.be", theme); //TODO good image
    }

    public Card(String text, String imageURL, Theme theme) {
        this.text = text;
        this.imageURL = imageURL;
        this.theme= theme;
        this.remarks = new ArrayList<>();
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
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

    @Override
    public Integer getId() {
        return cardId;
    }
}
