package be.kdg.kandoe.backend.dom.session;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.user.User;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Participation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Participation implements Serializable, Identifiable<Integer> {
    @Column(name = "ParticipationId", nullable = false)
    @Id
    @GeneratedValue
    private Integer participationId;

    @Column(name = "IsRegistered", nullable = false)
    private boolean isRegistered;

    @Column(name = "IsOnTurn", nullable = false)
    private boolean isOnTurn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Session.class, fetch = FetchType.EAGER, optional = true/* TODO optional = false*/)
    private Session session;

    @OneToMany(targetEntity = Card.class, mappedBy = "participationChoosen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> chosenCards;

    @OneToMany(targetEntity = Card.class, mappedBy = "participationAdded", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> addedCards;

    public Participation() {
        this.isRegistered = false;
        this.isOnTurn = false;
    }

    public Participation(boolean isRegistered, boolean isOnTurn) {
        this.isRegistered = isRegistered;
        this.isOnTurn = isOnTurn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public boolean isOnTurn() {
        return isOnTurn;
    }

    public void setIsOnTurn(boolean isOnTurn) {
        this.isOnTurn = isOnTurn;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<Card> getChosenCards() {
        return chosenCards;
    }

    public void setChosenCards(List<Card> chosenCards) {
        this.chosenCards = chosenCards;
    }

    public List<Card> getAddedCards() {
        return addedCards;
    }

    public void setAddedCards(List<Card> addedCards) {
        this.addedCards = addedCards;
    }

    @Override
    public Integer getId() {
        return participationId;
    }
}
