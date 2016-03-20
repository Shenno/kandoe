package be.kdg.kandoe.frontend.controllers.resources.users;

import java.io.Serializable;

/**
 * UserResourcePost is a Resource object for {link: UserResource} when posted for login
 */
public class UserResourcePost implements Serializable {
    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
