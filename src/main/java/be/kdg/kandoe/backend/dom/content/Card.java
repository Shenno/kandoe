package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Participation;
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

    @Column(name = "IsPicked", nullable = true)
    private boolean isPicked;

    @Column(name = "Priority", nullable = true)
    private int priority;

    @Column(name = "SnapshotID", nullable = true)
    private int snapShotID;

    @ManyToOne(targetEntity = Theme.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ThemeId")
    private Theme theme;

    @ManyToOne(targetEntity = Participation.class, fetch = FetchType.EAGER, optional = true) //TODO optinial -> false
    private Participation participationChoosen;  //deelnemer die kaartje heeft gekozen

    @ManyToOne(targetEntity = Participation.class, fetch = FetchType.EAGER, optional = true) //TODO optinial -> false
    private Participation participationAdded;  //deelnemer die kaartje heeft toegevoegd

    @OneToMany(targetEntity = Remark.class, mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Remark> remarks;

    @ManyToOne(targetEntity = Session.class, fetch = FetchType.EAGER, optional = true) //TODO optionial -> false
    private Session session;

    public Card(String text, Theme theme) {
        this(text, "www.google.be", theme); //TODO good image

    }

    public Card(String text, String imageURL, Theme theme) {
        this.text = text;
        this.imageURL = imageURL;
        this.isPicked = false;
        this.priority = 0;
        this.snapShotID = 1;
        this.theme= theme;
        this.remarks = new ArrayList<>();
    }
    public Theme getTheme() {
        return theme;
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

    public boolean isPicked() {
        return isPicked;
    }

    public void setIsPicked(boolean isPicked) {
        this.isPicked = isPicked;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getSnapShotID() {
        return snapShotID;
    }

    public void setSnapShotID(int snapShotID) {
        this.snapShotID = snapShotID;
    }

    @Override
    public Integer getId() {
        return cardId;
    }
}
