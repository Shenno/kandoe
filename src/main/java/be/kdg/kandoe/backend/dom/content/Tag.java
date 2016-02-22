package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Session;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A category of a {@link Theme}
 */
@Entity
@Table(name = "Tag")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Tag implements Serializable, Identifiable<Integer>{

    @Column(name = "TagId", nullable = false)
    @Id
    @GeneratedValue
    private Integer tagId;

    @Column(name = "TagName", nullable = false)
    private String tagName;

    @ManyToOne(targetEntity = Theme.class, fetch = FetchType.EAGER, optional = true) //TODO optional -> false
    private Theme theme;

    public Tag(String name) { //TODO Theme meegeven constructor
        this.tagName = name;
        //this.theme = theme;
    }

    public String getName() {
        return tagName;
    }

    public void setName(String name) {
        this.tagName = name;
    }

    @Override
    public Integer getId() {
        return tagId;
    }
}
