package be.kdg.kandoe.backend.dom.session;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.content.Remark;
import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Session")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries(
        {
                @NamedQuery(name = "Session.findSessionsByUserId", query = "SELECT s FROM Session s join s.users u WHERE u.userId = :userId")
        })
@DiscriminatorColumn(name = "SessionType", discriminatorType = DiscriminatorType.STRING)
public abstract class Session implements Serializable, Identifiable<Integer> {
    @Column(name = "SessionId", nullable = false)
    @Id
    @GeneratedValue
    private Integer sessionId;

    @Column(name="NameSession",nullable = false)
    private String nameSession;

    @Column(name="Problem",nullable= false)
    private boolean problem;

    @Column(name = "GameOver", nullable = false)
    private boolean gameOver;

    @Column(name = "MinCards", nullable = false)
    private int minCards;

    @Column(name = "MaxCards", nullable = false)
    private int maxCards;

    @Column(name= "AmountOfCircles", nullable = false)
    private int amountOfCircles;

    @Column(name = "SnapshotId", nullable = false)
    private int snapshotID;

    @Column(name = "CurrentRound", nullable = false)
    private Integer currentRound;

    @Column(name = "CurrentUser")
    private int currentUser;

    @Column(name = "Organisator")
    private int organisator;

    @ManyToOne(targetEntity = Theme.class, fetch = FetchType.EAGER, optional = false)
    private Theme theme;

    @OneToMany(targetEntity = Remark.class, mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Remark> remarks;

    @OneToMany(targetEntity = CardSession.class, mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<CardSession> cardSessions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserSession",
               joinColumns = @JoinColumn(name = "Sessionid", referencedColumnName = "SessionId"),
               inverseJoinColumns = @JoinColumn(name = "UserId", referencedColumnName = "UserId"))
    @Fetch(FetchMode.SELECT)
    private List<User> users = new ArrayList<>();

    public Session(boolean isProblem, String nameSession) {
        this(isProblem,1,20,nameSession);
    }

    public Session(boolean isProblem, int amountOfCircles, String nameSession) {
        this(isProblem,1,20,nameSession);
        this.amountOfCircles = amountOfCircles;
        this.remarks = new ArrayList<>();
    }

    public Session(boolean problem, int minCards, int maxCards, String nameSession) {
        this.problem = problem;
        this.gameOver = false;
        this.currentRound = 1;
        this.minCards = minCards;
        this.maxCards = maxCards;
        this.snapshotID = 1;
        this.currentUser = -1;
        this.nameSession = nameSession;
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

    public int getAmountOfCircles() {
        return amountOfCircles;
    }

    public void setAmountOfCircles(int amountOfCircles) {
        this.amountOfCircles = amountOfCircles;
    }

    public int getOrganisator() {
        return organisator;
    }

    public void setOrganisator(int organisator) {
        this.organisator = organisator;
    }

    public void addUser(User user) {
        this.users.add(user);
        if(currentUser == -1) {
            currentUser = user.getId();
        }
    }

    public void addRemark(Remark remark) {
        remark.setTimeStamp(LocalDateTime.now());
        this.remarks.add(remark);
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

    public Integer getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    public void addCardSession(CardSession cardSession) {
        this.cardSessions.add(cardSession);
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
    }

    public void updateCurrentUser() {
        User current = users.remove(0);
        currentUser = users.get(0).getUserId();
        users.add(current);
    }

    public String getNameSession() {
        return nameSession;
    }

    public void setNameSession(String nameSession) {
        this.nameSession = nameSession;
    }

    @Override
    public Integer getId() {
        return sessionId;
    }


}
