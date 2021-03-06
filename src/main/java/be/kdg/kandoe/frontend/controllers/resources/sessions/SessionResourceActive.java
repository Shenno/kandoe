package be.kdg.kandoe.frontend.controllers.resources.sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * SessionResourceActive is a Resource object for an active {link: Session}
 */
public class SessionResourceActive implements Serializable {
    private Integer id;
    private Integer currentUser;
    private List<CardSessionResource> cardSessionResources;
    private boolean problem;
    private boolean gameOver;
    private int amountOfCircles;
    private List<RemarkResource> remarks;
    private String nameSession;
    private int amountOfUsers;
    private String themeName;
    private int organisator;
    private String errorMessage;


    public SessionResourceActive() {
        this.cardSessionResources = new ArrayList<>();
        this.remarks = new ArrayList<>();
        this.errorMessage = "";
    }

    public List<CardSessionResource> getCardSessionResources() {
        return cardSessionResources;
    }

    public int getOrganisator() {
        return organisator;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setOrganisator(int organisator) {
        this.organisator = organisator;
    }

    public void setCardSessionResources(List<CardSessionResource> cardSessionResources) {
        this.cardSessionResources = cardSessionResources;
    }

    public List<RemarkResource> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<RemarkResource> remarks) {
        this.remarks = remarks;
    }

    public Integer getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Integer currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isProblem() {
        return problem;
    }

    public void setProblem(boolean problem) {
        this.problem = problem;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getAmountOfCircles() {
        return amountOfCircles;
    }

    public void setAmountOfCircles(int amountOfCircles) {
        this.amountOfCircles = amountOfCircles;
    }

    public String getNameSession() {
        return nameSession;
    }

    public void setNameSession(String nameSession) {
        this.nameSession = nameSession;
    }

    public int getAmountOfUsers() {
        return amountOfUsers;
    }

    public void setAmountOfUsers(int amountOfUsers) {
        this.amountOfUsers = amountOfUsers;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

