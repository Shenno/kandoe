package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;
import org.springframework.cglib.core.Local;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Remark")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Remark implements Serializable, Identifiable<Integer> {

    @Column(name = "RemarkId", nullable = false)
    @Id
    @GeneratedValue
    private Integer remarkId;

    @Column(name = "Text", nullable = false)
    private String text;

    @Column(name = "TimeStamp", nullable = false)
    private LocalDateTime timeStamp;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false)
    private User user;

    @ManyToOne(targetEntity = Session.class, fetch = FetchType.EAGER, optional = true)
    private Session session;

    @ManyToOne(targetEntity = Card.class, fetch = FetchType.EAGER, optional = true)
    private Card card;

    public Remark(String text) {
        this.text = text;
        this.timeStamp = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public Integer getId() {
        return remarkId;
    }
}
