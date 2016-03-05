package be.kdg.kandoe.frontend.controllers.resources.sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 5/03/2016.
 */
public class SessionResourceActive implements Serializable {
    private Integer currentUser;
    private List<CardSessionResource> cardSessionResources;
    private boolean problem;
    private int amountOfCircles;

    public SessionResourceActive() {
        this.cardSessionResources = new ArrayList<>();
    }

    public List<CardSessionResource> getCardSessionResources() {
        return cardSessionResources;
    }

    public void setCardSessionResources(List<CardSessionResource> cardSessionResources) {
        this.cardSessionResources = cardSessionResources;
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

    public int getAmountOfCircles() {
        return amountOfCircles;
    }

    public void setAmountOfCircles(int amountOfCircles) {
        this.amountOfCircles = amountOfCircles;
    }
}

