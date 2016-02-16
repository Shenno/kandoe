package Sesion;

import java.util.Date;

/**
 * Created by Len on 10-2-2016.
 */
public class SynchronousSession extends Session {

    private Date StartingTime;

    public SynchronousSession(boolean isEnded, int currentRound, int minCards, int maxCards, int snapshotID) {
        super(isEnded, currentRound, minCards, maxCards, snapshotID);
    }


    public Date getStartingTime() {
        return StartingTime;
    }

    public void setStartingTime(Date startingTime) {
        StartingTime = startingTime;
    }
}
