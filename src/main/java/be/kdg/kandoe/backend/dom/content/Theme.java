package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Theme")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Theme implements Serializable, Identifiable<Integer> {

    @Column(name = "ThemeId", nullable = false)
    @Id
    @GeneratedValue
    private Integer themeId;

    @Column(name = "ThemeName", nullable = false)
    private String themeName;

    @Column(name = "Description", nullable = true)
    private String description;

    @Column(name = "IsCommentaryAllowed", nullable = false)
    private boolean isCommentaryAllowed;

    @Column(name = "IsAddingAdmited", nullable = false)
    private boolean isAddingAdmited;

    @ManyToMany
    @JoinTable(
            name="Org_Theme",
            joinColumns=@JoinColumn(name="ThemeId", referencedColumnName="ThemeId"),
            inverseJoinColumns=@JoinColumn(name="UserId", referencedColumnName="UserId"))
    private List<User> organisators;

    @ManyToOne(targetEntity = Organisation.class, fetch = FetchType.EAGER, optional = false)
    private Organisation organisation;

    @OneToMany(targetEntity = Tag.class, mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags;

    @OneToMany(targetEntity = Card.class, mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> cards;

    @OneToMany(targetEntity = Session.class, mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Session> sessions;

   /* @OneToMany(targetEntity = Theme.class, mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Theme> subthemes;*/

    public Theme(String name, String description, User organisator, Organisation organisation, List<Tag> tags) {
        this(name, description, true, true, organisator, organisation, tags);
    }

    public Theme(String name, String description, boolean isCommentaryAllowed, boolean isAddingAdmited, User organisator, Organisation organisation, List<Tag> tags) {
        this.themeName = name;
        this.description = description;
        this.isCommentaryAllowed = isCommentaryAllowed;
        this.isAddingAdmited = isAddingAdmited;
        this.organisators = new ArrayList<>();
        organisators.add(organisator);
        this.cards = new ArrayList<>();
        this.sessions = new ArrayList<>();
        /*this.subthemes = new ArrayList<>();*/
        this.organisation = organisation;
        this.tags = tags;

    }

    public String getName() {
        return themeName;
    }

    public void setName(String name) {
        this.themeName = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCommentaryAllowed() {
        return isCommentaryAllowed;
    }

    public void setIsCommentaryAllowed(boolean isCommentaryAllowed) {
        this.isCommentaryAllowed = isCommentaryAllowed;
    }

    public boolean isAddingAdmited() {
        return isAddingAdmited;
    }

    public void setIsAddingAdmited(boolean isAddingAdmited) {
        this.isAddingAdmited = isAddingAdmited;
    }

    public List<User> getOrganisator() {
        return organisators;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public Integer getId() {
        return themeId;
    }
}
