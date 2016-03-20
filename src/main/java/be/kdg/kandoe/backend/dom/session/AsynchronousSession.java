package be.kdg.kandoe.backend.dom.session;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * A Asynchronous Session is an implementation of {@link Session},
 * where everyone can pllay at the same time.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("AsynchronousSession")
public class AsynchronousSession extends Session implements Serializable{

    @Column(name = "MaxTimeRound", nullable = false)
    private int maxTimeRound; //in seconds

    public AsynchronousSession() {
        this.maxTimeRound = 60;

    }

    public AsynchronousSession(int organisator, boolean isProblem, int maxTimeRound, int amountOfCircles,String nameSession) {
        super(organisator, isProblem, amountOfCircles,nameSession);
        this.maxTimeRound = maxTimeRound;
    }

    public int getMaxTimeRound() {
        return maxTimeRound;
    }

    public void setMaxTimeRound(int maxTimeRound) {
        this.maxTimeRound = maxTimeRound;
    }
}
