package be.kdg.kandoe.backend.dom.session;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("AsynchronousSession")
public class AsynchronousSession extends Session implements Serializable{

    @Column(name = "MaxTimeRound", nullable = false)
    private int maxTimeRound; //in seconds

    public AsynchronousSession() {
        //super(false,"sessionName");
        this.maxTimeRound = 60;

    }

    public AsynchronousSession(int organisator, boolean isProblem, int maxTimeRound, int amountOfCircles,String nameSession) {
        super(organisator, isProblem, amountOfCircles,nameSession);
        this.maxTimeRound = maxTimeRound;
    }

/*    public AsynchronousSession(boolean isProblem,int minCards, int maxCards, int maxTimeRound, String nameSession) {
        super(isProblem,minCards, maxCards, nameSession);
        this.maxTimeRound = maxTimeRound;
    }*/

    public int getMaxTimeRound() {
        return maxTimeRound;
    }

    public void setMaxTimeRound(int maxTimeRound) {
        this.maxTimeRound = maxTimeRound;
    }
}
