package be.kdg.kandoe.backend.dom.session;

import java.util.Date;

/**
 * Created by Len on 10-2-2016.
 */
public class AsynchronousSession extends Session {

    private Date maxTimeRound;

    public AsynchronousSession(boolean isEnded, int currentRound, int minCards, int maxCards, int snapshotID) {
        super(isEnded, currentRound, minCards, maxCards, snapshotID);
    }


    public Date getMaxTimeRound() {
        return maxTimeRound;
    }

    public void setMaxTimeRound(Date maxTimeRound) {
        this.maxTimeRound = maxTimeRound;
    }
}
