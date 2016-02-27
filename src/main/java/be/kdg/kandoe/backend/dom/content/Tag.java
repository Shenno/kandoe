package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Session;
import org.hibernate.annotations.Cascade;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A category of a {@link Theme}
 */
@Entity
@Table(name = "Tag")
@NamedQueries(
        {
                @NamedQuery(name = "Tag.findByTagNamebyTheme", query = "SELECT t FROM Tag t WHERE t.tagName = :tagname and t.theme = :theme"),
        })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Tag implements Serializable, Identifiable<Integer>{

    @Column(name = "TagId", nullable = false)
    @Id
    @GeneratedValue
    private Integer tagId;

    @Column(name = "TagName", nullable = false)
    private String tagName;

    @ManyToOne(targetEntity = Theme.class, fetch = FetchType.EAGER, optional = false)
    private Theme theme;

    public Tag(){

    }

    public Tag(String name, Theme theme) {
        this.tagName = name;
        this.theme = theme;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String name) {
        this.tagName = name;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public Integer getId() {
        return tagId;
    }
}
