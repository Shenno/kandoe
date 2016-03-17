package be.kdg.kandoe.backend.dom.user;

import be.kdg.kandoe.backend.dom.content.Theme;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An Organisations is made by a {link: User} and has {link:Theme} objects
 */
@Entity
@Table(name = "Organisation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries(
        {
                @NamedQuery(name = "Organisation.findOrganisationByName", query = "SELECT o FROM Organisation o WHERE o.organisationName = :name")
        }
)

public class Organisation implements Serializable, Identifiable<Integer> {

    @Column(name = "OrganisationId", nullable = false)
    @Id
    @GeneratedValue
    private Integer organisationId;

    @Column(name = "OrganisationName", nullable = false)
    private String organisationName;

    @OneToMany(targetEntity = Theme.class, mappedBy = "organisation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Theme> themes;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Organisation() {

    }
    public Organisation(String name) {
        organisationName = name;
        this.themes =  new ArrayList<>();
    }
    public Organisation(String name, User user) {
        organisationName = name;
        this.user = user;
        this.themes =  new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return organisationId;
    }

    public Integer getOrganisationId() {
        return getId();
    }

    public String getName() {
        return organisationName;
    }

    public void setName(String name) {
        organisationName = name;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public User getOrganisator() {
        return user;
    }

    public void setOrganisator(User organisator) {
        this.user = organisator;
    }
}
