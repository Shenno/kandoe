package be.kdg.kandoe.frontend.config.security.jwt;

import be.kdg.kandoe.backend.services.api.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import io.jsonwebtoken.Jwts;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Kevin on 29/02/2016.
 */

@Component
public class JwtFilter extends GenericFilterBean {

    private UserService userService;

    public JwtFilter() {

    }

    public JwtFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String authHeader = httpRequest.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            SecurityContextHolder.getContext().setAuthentication(null);

        } else {
            String token = authHeader.substring(7);
            token=token.replace("\"","");
            String username = Jwts.parser().setSigningKey("toomanysecrets")
                    .parseClaimsJws(token).getBody().getSubject();
            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(userService.findUserByUsername(username)));

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
