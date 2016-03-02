package be.kdg.kandoe.frontend.config.security.jwt;

/**
 * Created by Kevin on 29/02/2016.
 */

public class LoginResponse {
    private String token;

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
