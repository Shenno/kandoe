package be.kdg.kandoe.frontend.controllers.resources.users;

/**
 * Created by   Shenno Willaert
 * Date         26/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.frontend.controllers.resources.users
 */
public class UserResourcePost {
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