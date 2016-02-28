package be.kdg.kandoe.frontend.controllers.resources.users;

import be.kdg.kandoe.backend.dom.user.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.List;

/**
 * UserResource is used to send User information over the wire.
 */
public class UserResource implements Serializable
{
    // User properties
    private Integer id;

    private String username;

   /* public UserResource(User user)
    {
        this.userId = user.getUserId();
        this.username = user.getUsername();
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

}
