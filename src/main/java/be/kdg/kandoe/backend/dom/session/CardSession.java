package be.kdg.kandoe.backend.dom.session;

import be.kdg.kandoe.backend.dom.content.Card;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A CardSession is a card for a {@link Session}
 */

@Entity
public class CardSession implements Serializable, Identifiable<Integer>, Comparable<CardSession> {

    @Id
    @Column(name = "CardSessionId", nullable = false)
    @GeneratedValue
    private Integer cardSessionId;

    @Column(name = "DistanceToCenter", nullable = false)
    private int distanceToCenter;

    @Column(name = "CardText")
    private String card;

    @Column(name = "Image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    public CardSession(int distanceToCenter, String card, String image, Session session) {
        this.distanceToCenter = distanceToCenter;
        this.card = card;
        this.image = image;
        this.session = session;
    }

    public CardSession() {
        new CardSession(0, null, null, null);
    }

    public int getDistanceToCenter() {
        return distanceToCenter;
    }

    public void setDistanceToCenter(int distanceToCenter) {
        this.distanceToCenter = distanceToCenter;
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

    @Override
    public int compareTo(CardSession cs) {
        return this.card.compareToIgnoreCase(cs.card);
    }

    @Override
    public String toString() {
        return card;
    }
}
