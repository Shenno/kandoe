package be.kdg.kandoe.backend.dom.session;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.User;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(name="Problem",nullable= false)
    private boolean problem;

    @Column(name = "GameOver", nullable = false)
    private boolean gameOver;

    @Column(name = "MinCards", nullable = false)
    private int minCards;

    @Column(name = "MaxCards", nullable = false)
    private int maxCards;

    @Column(name = "SnapshotId", nullable = false)
    private int snapshotID;

    @Column(name = "CurrentRound", nullable = false)
    private int currentRound;

    @ManyToOne(targetEntity = Theme.class, fetch = FetchType.EAGER, optional = false)
    private Theme theme;

 /*   @OneToMany(targetEntity = Participation.class, mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participation> participations;*/

    @OneToMany(targetEntity = Remark.class, mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Remark> remarks;

    @OneToMany(targetEntity = CardSession.class, mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CardSession> cardSessions = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE })
    @JoinTable(name = "UserSession",
               joinColumns = @JoinColumn(name = "Sessionid", referencedColumnName = "SessionId"),
               inverseJoinColumns = @JoinColumn(name = "UserId", referencedColumnName = "UserId"))
    private List<User> users = new ArrayList<>();

/*    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Card_Session",
            joinColumns = @JoinColumn(name = "SessionId", referencedColumnName = "SessionId"),
            inverseJoinColumns = @JoinColumn(name = "CardId", referencedColumnName = "CardId"))
    private List<Card> cards = new ArrayList<>();*/

    public Session(boolean isProblem) {
        this(isProblem,1,20);
    }

    public Session(boolean problem, int minCards, int maxCards) {
        this.problem = problem;
        this.gameOver = false;
        this.currentRound = 1;
        this.minCards = minCards;
        this.maxCards = maxCards;
        this.snapshotID = 1;
    }

    public List<CardSession> getCardSessions() {
        return cardSessions;
    }

    public void setCardSessions(List<CardSession> cardSessions) {
        this.cardSessions = cardSessions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
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

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public boolean isProblem() {
        return problem;
    }

    public void setProblem(boolean problem) {
        this.problem = problem;
    }

    public void addCardSession(CardSession cardSession) {
        this.cardSessions.add(cardSession);
    }

    @Override
    public Integer getId() {
        return sessionId;
    }
}
