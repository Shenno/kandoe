package be.kdg.kandoe.backend.dom.user;

import be.kdg.kandoe.backend.dom.content.Theme;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private List<Theme> themes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Dit is de organisator maar moet user heten voor namingConvention hibernate TODO aanpassen!

    public Organisation() {

    }

    public Organisation(String name) {
        organisationName = name;
        this.themes =  new ArrayList<>();
    }


    public String getName() {
        return organisationName;
    }

    public void setName(String name) {
        organisationName = name;
    }

    @Override
    public Integer getId() {
        return organisationId;
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
