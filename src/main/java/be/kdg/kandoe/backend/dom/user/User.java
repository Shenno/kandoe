package be.kdg.kandoe.backend.dom.user;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.Participation;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.springframework.hateoas.Identifiable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Abstract class representing a user of the system. Every user is associated
 * with a Person. Every user has to have a unique username. This username takes
 * the form of an email address.
 *
 * Shenno.willaert okk
 *
 * @author wouter
 */
@Entity
@Table(name = "`User`")
@NamedQueries(
        {
                @NamedQuery(name = "User.findByUserid", query = "SELECT u FROM User u WHERE u.userId = :userid"),
                @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
        })
public class User implements Serializable, UserDetails, Identifiable<Integer>
{
    @Id
    @GeneratedValue
    @Column(name = "UserId", nullable = false)
    private Integer userId;

    @Column(name = "Username", nullable = true, length = 255, unique = true)
    private String username = null;

    @Column(name = "Password", nullable = true, length = 255)
    private String encryptedPassword;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="Org_Theme",
            inverseJoinColumns=@JoinColumn(name="ThemeId", referencedColumnName="ThemeId"),
            joinColumns=@JoinColumn(name="UserId", referencedColumnName="UserId"))
    private List<Theme> themes=new ArrayList<>();

    @OneToMany(targetEntity = Participation.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participation> participations;

    @OneToMany(targetEntity = Organisation.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Organisation> organisations;

    public User()
    {
        //Deze constructor doet niet veel
    }

    public User(String username, String encryptedPassword)
    {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.organisations = new ArrayList<>();
    }

    public Integer getId()
    {
        return userId;
    }

    public void setId(Integer id)
    {
        this.userId = id;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void addParticipation(Participation participation) {
        participations.add(participation);
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(List<Organisation> organisations) {
        this.organisations = organisations;
    }

    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (encryptedPassword != null ? encryptedPassword.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (encryptedPassword != null ? !encryptedPassword.equals(user.encryptedPassword) : user.encryptedPassword != null)
            return false;
        return true;

    }

    @Override
    public String toString()
    {
        return "User{" + userId + ", " + username + ", " + encryptedPassword + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return getEncryptedPassword();
    }

    /**
     * Get username for this user
     *
     * @return
     */
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public String getEncryptedPassword()
    {
        return encryptedPassword;
    }

    /**
     * Change password for user
     *
     * @param encryptedPassword
     * @throws UserServiceException
     */
    public synchronized void setEncryptedPassword(String encryptedPassword)
    {
        this.encryptedPassword = encryptedPassword;
    }


}
