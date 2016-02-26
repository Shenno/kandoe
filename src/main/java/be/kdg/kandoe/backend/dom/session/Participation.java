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

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false)
    private User user;

    @ManyToOne(targetEntity = Session.class, fetch = FetchType.EAGER, optional = false)
    private Session session;

    @OneToMany(targetEntity = Card.class, mappedBy = "participationChoosen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> chosenCards;

    @OneToMany(targetEntity = Card.class, mappedBy = "participationAdded", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> addedCards;

    public Participation() {
        this.isRegistered = false;
        this.isOnTurn = false;
    }

    public User getUser() {
        return user;
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

    @Override
    public Integer getId() {
        return participationId;
    }
}
