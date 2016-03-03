package be.kdg.kandoe.frontend.config.security;

import be.kdg.kandoe.backend.dom.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kevin on 29/02/2016.
 */

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();
        String token = Jwts.builder().setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, "toomanysecrets").compact();
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(token);
    }
}
