package be.kdg.kandoe.frontend.controllers.resources.users;

import be.kdg.kandoe.backend.dom.user.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.List;

/**
 * UserResource is used to send User information over the wire.
 */
public class UserResource extends ResourceSupport implements Serializable
{
    // User properties
    private Integer userId;

    @Email
    private String username;

    private String password;

    public UserResource(User user)
    {
        this.userId = user.getUserId();
        this.username = user.getUsername();
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


}
