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

    @Column(name = "StartingTime", nullable = false)
    private LocalDateTime startingTime;

    public SynchronousSession(LocalDateTime startingTime) {
        super();
        this.startingTime = startingTime;
    }

    public SynchronousSession(int minCards, int maxCards, LocalDateTime startingTime) {
        super(minCards, maxCards);
        this.startingTime = startingTime;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }
}
