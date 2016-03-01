package be.kdg.kandoe.backend.dom.content;

import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A subject for a {@link Session}
 */
@Entity
@Table(name = "Theme")
@NamedQueries(
        {
                @NamedQuery(name = "Theme.findByThemeNameByOrganisation", query = "SELECT t FROM Theme t WHERE t.themeName = :themename and t.organisation = :organisation"),
                @NamedQuery(name = "Theme.findThemesByOrganisation",query = "SELECT t FROM Theme t where t.organisation = :organisation")
        })
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

    @Column(name = "CommentaryAllowed", nullable = false)
    private boolean commentaryAllowed;

    @Column(name = "AddingAdmited", nullable = false)
    private boolean addingAdmitted;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Org_Theme",
            joinColumns = @JoinColumn(name = "ThemeId", referencedColumnName = "ThemeId"),
            inverseJoinColumns = @JoinColumn(name = "UserId", referencedColumnName = "UserId"))
    private List<User> organisators = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @OneToMany(targetEntity = Tag.class, mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags;

    @OneToMany(targetEntity = Card.class, mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> cards;

    @OneToMany(targetEntity = Session.class, mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Session> sessions;

    @ManyToOne(targetEntity = Theme.class, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "ThemeId", insertable = false, updatable = false)
    private Theme mainTheme;

    @OneToMany(targetEntity = Theme.class, mappedBy = "mainTheme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Theme> subthemes;

    public Theme() {
    }

    public Theme(String name, String description, User organisator, Organisation organisation, List<Tag> tags) {
        this(name, description, true, true, organisator, organisation, tags);
    }

    public Theme(String name, String description, boolean commentaryAllowed, boolean addingAdmitted, User organisator, Organisation organisation, List<Tag> tags) {
        this.themeName = name;
        this.description = description;
        this.commentaryAllowed = commentaryAllowed;
        this.addingAdmitted = addingAdmitted;
        this.organisators = new ArrayList<>();
        addOrganisator(organisator);
        this.organisation = organisation;
        this.tags = tags;
        this.cards = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.subthemes = new ArrayList<>();

    }

    public String getThemeName() {
        return themeName;
    }

    @Override
    public Integer getId() {
        return themeId;
    }

    public List<User> getOrganisators() {
        return organisators;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCommentaryAllowed() {
        return commentaryAllowed;
    }

    public boolean isAddingAdmitted() {
        return addingAdmitted;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void addOrganisator(User user) {
        if (user != null) {
            organisators.add(user);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    //todo delete

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCommentaryAllowed(boolean commentaryAllowed) {
        this.commentaryAllowed = commentaryAllowed;
    }

    public void setAddingAdmitted(boolean addingAdmitted) {
        this.addingAdmitted = addingAdmitted;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
}
