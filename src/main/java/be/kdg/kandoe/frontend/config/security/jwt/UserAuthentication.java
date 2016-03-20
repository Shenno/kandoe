package be.kdg.kandoe.frontend.config.security.jwt;

import be.kdg.kandoe.backend.dom.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * UserAuthentication is a class that is responsible for the authentication of a {link: User}
 */
public class UserAuthentication implements Authentication {

    private User user;
    private boolean authenticated;

    public UserAuthentication(User user) {
        this.user = user;
        authenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }


}
