package be.kdg.kandoe.frontend.controllers.rest;


import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResource;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResourcePost;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResourceRegister;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
/**
 * RestController for everything with Login
 */
@RequestMapping("/api")
@RestController
public class LoginRestController {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MapperFacade mapperFacade;

    private Logger logger = Logger.getLogger(LoginRestController.class);

    @Autowired
    public LoginRestController(BCryptPasswordEncoder passwordEncoder, UserService userService, MapperFacade mapperFacade) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.mapperFacade = mapperFacade;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@Valid @RequestBody UserResourceRegister userResourceRegister) {
        User u = mapperFacade.map(userResourceRegister, User.class);
        userService.addUser(u);
        String token = Jwts.builder().setSubject(u.getUsername())
                .signWith(SignatureAlgorithm.HS256, "toomanysecrets").compact();
        logger.info("User " + userResourceRegister.getUsername() + " has been created");
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserResourcePost userResourcePost, @AuthenticationPrincipal User user) {
        try {
            User u = userService.findUserByUsername(userResourcePost.getUsername());
            if (passwordEncoder.matches(userResourcePost.getPassword(), u.getEncryptedPassword())) {
                String token = Jwts.builder().setSubject(u.getUsername())
                        .signWith(SignatureAlgorithm.HS256, "toomanysecrets").compact();
                logger.info("User " + u.getUsername() + " has succesfully logged in");
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                logger.warn("Wrong username or password");
                return new ResponseEntity<>("Wrong username or password", HttpStatus.UNAUTHORIZED);
            }
        } catch (UserServiceException e) {
            logger.warn("Failed to login because: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
