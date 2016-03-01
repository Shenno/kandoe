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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id")
    private Session session;

    public CardSession(int priority, Card card, Session session) {
        this.priority = priority;
        this.card = card;
        this.session = session;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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
