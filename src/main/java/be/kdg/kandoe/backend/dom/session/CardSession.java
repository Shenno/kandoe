package be.kdg.kandoe.backend.dom.session;

import be.kdg.kandoe.backend.dom.content.Card;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by   Shenno Willaert
 * Date         1/03/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.dom.session
 */

@Entity
public class CardSession implements Serializable, Identifiable<Integer> {

    @Id
    @Column(name = "CardSessionId", nullable = false)
    @GeneratedValue
    private Integer cardSessionId;

    @Column(name = "Priority", nullable = false)
    private int priority;

    @Column(name = "CardText")
    private String card;

    @Column(name = "Image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    public CardSession(int priority, String card, String image, Session session) {
        this.priority = priority;
        this.card = card;
        this.image = image;
        this.session = session;
    }

    public CardSession() {
        new CardSession(0, null, null, null);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Integer getId() {
        return cardSessionId;
    }
}
