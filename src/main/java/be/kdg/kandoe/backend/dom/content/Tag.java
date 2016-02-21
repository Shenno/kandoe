package be.kdg.kandoe.backend.dom.content;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;

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

    public Tag(String name) {
        this.tagName = name;
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
