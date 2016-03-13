package be.kdg.kandoe.backend.dom.session;

import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SynchronousSession")
public class SynchronousSession extends Session implements Serializable{

    @Column(name = "StartingTime")
    private LocalDateTime startingTime;

    public SynchronousSession(boolean isProblem,LocalDateTime startingTime,String nameSession) {
        super(isProblem, nameSession);
        this.startingTime = startingTime;
    }

    public SynchronousSession(boolean isProblem, int minCards, int maxCards, LocalDateTime startingTime, String nameSession) {
        super(isProblem,minCards, maxCards, nameSession);
        this.startingTime = startingTime;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }
}
