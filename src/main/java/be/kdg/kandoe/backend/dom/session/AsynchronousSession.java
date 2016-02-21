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
    private LocalDateTime maxTimeRound;

    public AsynchronousSession(boolean isProblem, LocalDateTime maxTimeRound) {
        super(isProblem);
        this.maxTimeRound = maxTimeRound;
    }

    public AsynchronousSession(boolean isProblem,int minCards, int maxCards, LocalDateTime maxTimeRound) {
        super(isProblem,minCards, maxCards);
        this.maxTimeRound = maxTimeRound;
    }

    public LocalDateTime getMaxTimeRound() {
        return maxTimeRound;
    }

    public void setMaxTimeRound(LocalDateTime maxTimeRound) {
        this.maxTimeRound = maxTimeRound;
    }
}
