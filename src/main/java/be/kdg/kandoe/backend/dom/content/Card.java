package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Participation;
import be.kdg.kandoe.backend.dom.session.Session;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name = "Text", nullable = false)
    private String text;

    @Column(name = "ImageURL", nullable = true)
    private String imageURL;

    @Column(name = "IsPicked", nullable = false)
    private boolean isPicked;

    @Column(name = "Priority", nullable = false)
    private int priority;

    @Column(name = "SnapshotID", nullable = false)
    private int snapShotID;

    @ManyToOne(targetEntity = Theme.class, fetch = FetchType.EAGER, optional = true)
    private Theme theme;

    @ManyToOne(targetEntity = Participation.class, fetch = FetchType.EAGER, optional = true) //TODO optinial -> false
    private Participation participationChoosen;  //deelnemer die kaartje heeft gekozen

    @ManyToOne(targetEntity = Participation.class, fetch = FetchType.EAGER, optional = true) //TODO optinial -> false
    private Participation participationAdded;  //deelnemer die kaartje heeft toegevoegd

    @OneToMany(targetEntity = Remark.class, mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Remark> remarks;

    @ManyToOne(targetEntity = Session.class, fetch = FetchType.EAGER, optional = true) //TODO optinial -> false
    private Session session;

    public Card(String text) {
        this(text, "");
    }

    public Card(String text, String imageURL) {
        this.text = text;
        this.imageURL = imageURL;
        this.isPicked = false;
        this.priority = 0;
        this.snapShotID = 1;
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
