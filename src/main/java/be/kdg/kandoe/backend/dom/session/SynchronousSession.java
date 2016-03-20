package be.kdg.kandoe.backend.dom.session;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A Synchronous Session is an implementation of {@link Session},
 * where everyone plays turn by turn
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SynchronousSession")
public class SynchronousSession extends Session implements Serializable{

    @Column(name = "StartingTime")
    private LocalDateTime startingTime;

    public SynchronousSession() {}

    /*public SynchronousSession(boolean isProblem,LocalDateTime startingTime,String nameSession) {
        super(isProblem, nameSession);
        this.startingTime = startingTime;
    }

    public SynchronousSession(boolean isProblem, int minCards, int maxCards, LocalDateTime startingTime, String nameSession) {
        super(isProblem,minCards, maxCards, nameSession);
        this.startingTime = startingTime;
    }*/

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }
}
