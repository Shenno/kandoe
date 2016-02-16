package be.kdg.kandoe.backend.dom.session;

/**
 * Created by Len on 10-2-2016.
 */
public class Session {
    private boolean isEnded=false;
    private int minCards;
    private int maxCards;
    private int snapshotID;
    private int currentRound;

    public Session(boolean isEnded, int currentRound, int minCards, int maxCards, int snapshotID) {
        this.isEnded = isEnded;
        this.currentRound = currentRound;
        this.minCards = minCards;
        this.maxCards = maxCards;
        this.snapshotID = snapshotID;
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
}
