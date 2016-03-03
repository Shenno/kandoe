package be.kdg.kandoe.frontend.controllers.resources.users;

import java.io.Serializable;

/**
 * Created by Kevin on 3/03/2016.
 */

public class UserResourceRegister implements Serializable {
    public String username;
    public String password;
    public String firstName;
    public String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}