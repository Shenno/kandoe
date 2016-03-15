package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;
import org.springframework.cglib.core.Local;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * A Remark for a {@link Session} -> Chat
 * Or a Remark for a {@link Card} -> Remark
 */
@Entity
@Table(name = "Remark")
@NamedQueries(
        {
                @NamedQuery(name = "Remark.findByTextByCard", query = "SELECT r FROM Remark r WHERE r.text = :text and r.card = :card"),
        })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Remark implements Serializable, Identifiable<Integer> {

    @Column(name = "RemarkId", nullable = false)
    @Id
    @GeneratedValue
    private Integer remarkId;

    @Column(name = "Text", nullable = false)
    private String text;

    @Column(name = "TimeStamp", nullable = false)
    private LocalDateTime timeStamp;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false)
    private User user;

    @ManyToOne(targetEntity = Session.class, fetch = FetchType.EAGER, optional = true)
    private Session session;

    @ManyToOne(targetEntity = Card.class, fetch = FetchType.EAGER, optional = true)
    private Card card;

    public Remark(){

    }
    public Remark(String text,User user, Card card) {
        this.text = text;
        this.timeStamp = LocalDateTime.now();
        this.user = user;
        this.card = card;
        this.session = null;
    }
    public Remark(String text,User user, Session session) {
        this.text = text;
        this.timeStamp = LocalDateTime.now();
        this.user = user;
        this.card = null;
        this.session = session;
    }
    @Override
    public Integer getId() {
        return remarkId;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


}
