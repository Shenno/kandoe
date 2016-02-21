package be.kdg.kandoe.backend.dom.session;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Theme;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Session")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SessionType", discriminatorType = DiscriminatorType.STRING)
public abstract class Session implements Serializable, Identifiable<Integer> {
    @Column(name = "SessionId", nullable = false)
    @Id
    @GeneratedValue
    private Integer sessionId;

    @Column(name = "IsEnded", nullable = false)
    private boolean isEnded=false;

    @Column(name = "MinCards", nullable = false)
    private int minCards;

    @Column(name = "MaxCards", nullable = false)
    private int maxCards;

    @Column(name = "SnapshotId", nullable = false)
    private int snapshotID;

    @Column(name = "CurrentRound", nullable = false)
    private int currentRound;

    @OneToMany(targetEntity = Card.class, mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> cards;

    @ManyToOne(targetEntity = Theme.class, fetch = FetchType.EAGER, optional = false)
    private Theme theme;

    @OneToMany(targetEntity = Participation.class, mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participation> participations;

    public Session() {
        this(1,20);
    }

    public Session(int minCards, int maxCards) {
        this.isEnded = false;
        this.currentRound = 1;
        this.minCards = minCards;
        this.maxCards = maxCards;
        this.snapshotID = 1;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }

    public int getMinCards() {
        return minCards;
    }

    public void setMinCards(int minCards) {
        this.minCards = minCards;
    }

    public int getMaxCards() {
        return maxCards;
    }

    public void setMaxCards(int maxCards) {
        this.maxCards = maxCards;
    }

    public int getSnapshotID() {
        return snapshotID;
    }

    public void setSnapshotID(int snapshotID) {
        this.snapshotID = snapshotID;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    @Override
    public Integer getId() {
        return sessionId;
    }
}
