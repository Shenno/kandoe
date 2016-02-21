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

    public AsynchronousSession(LocalDateTime maxTimeRound) {
        super();
        this.maxTimeRound = maxTimeRound;
    }

    public AsynchronousSession(int minCards, int maxCards, LocalDateTime maxTimeRound) {
        super(minCards, maxCards);
        this.maxTimeRound = maxTimeRound;
    }

    public LocalDateTime getMaxTimeRound() {
        return maxTimeRound;
    }

    public void setMaxTimeRound(LocalDateTime maxTimeRound) {
        this.maxTimeRound = maxTimeRound;
    }
}
