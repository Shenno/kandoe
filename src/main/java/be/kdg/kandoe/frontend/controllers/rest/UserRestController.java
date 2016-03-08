package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import be.kdg.kandoe.frontend.controllers.resources.content.ThemeResource;
import be.kdg.kandoe.frontend.controllers.resources.users.OrganisationResource;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResource;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResourcePost;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResourceRegister;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@ExposesResourceFor(UserResource.class)
public class UserRestController {
    private final BCryptPasswordEncoder passwordEncoder;
    private final Logger logger = Logger.getLogger(UserRestController.class);
    private final UserService userService;
    private final MapperFacade mapperFacade;
    //private final UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserRestController(UserService userService,
                              MapperFacade mapperFacade, BCryptPasswordEncoder passwordEncoder
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.mapperFacade = mapperFacade;
        // this.userResourceAssembler = userResourceAssembler;
    }

    @RequestMapping(value = "/users/me", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getCurrentUser(@AuthenticationPrincipal User user) {
        //No need to check for user being null, API call is authenticated
        return new ResponseEntity<UserResource>(mapperFacade.map(user, UserResource.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/info", method = RequestMethod.GET)
    public String test() {
        return "This is the API to handle UserController";
    }


/*    @InitBinder("userResource")
    public void initBinder(WebDataBinder webDataBinder)
    {
        webDataBinder.addValidators(new EmailValidator());
    }
    */


    /*    @RequestMapping(method = RequestMethod.GET)
        public ResponseEntity<List<UserResource>> findUsers()
        {
            List<User> users = userService.findUsers();
            List<UserResource> resources = new ArrayList<>();
            for(User u : users) {
                UserResource userResource = new UserResource(u);
                resources.add(userResource);
            }
            return new ResponseEntity<>(resources, HttpStatus.OK);
        }
    */

    @RequestMapping(value = "/usernames", method = RequestMethod.GET)
    public ResponseEntity<List<String>> findUsernames() {
        List<String> usernames = userService.findUsernames();
        return new ResponseEntity<>(usernames, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserResource>> findUsers() {
        List<User> users = userService.findUsers();

        List<UserResource> userResources = users.stream().map(u -> mapperFacade.map(u, UserResource.class)).collect(Collectors.toList());
        return new ResponseEntity<>(userResources, HttpStatus.OK);
    }

    @RequestMapping(value = "users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> findUserById(@PathVariable int userId) {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(mapperFacade.map(user, UserResource.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/organisations", method = RequestMethod.GET)
    public ResponseEntity<List<OrganisationResource>> findorganisations() {
        List<Organisation> organisations = this.userService.findOrganisations();
      /*  List<OrganisationResource> organisationResources = new ArrayList<>();
        for(Organisation o : organisations) {
            organisationResources.add(new OrganisationResource(o));
        }*/
        List<OrganisationResource> organisationResources = organisations.stream().map(o -> mapperFacade.map(o, OrganisationResource.class)).collect(Collectors.toList());
        return new ResponseEntity<>(organisationResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/organisations/{organisationId}", method = RequestMethod.GET)
    public ResponseEntity<OrganisationResource> findorganisation(@PathVariable int organisationId) {
        Organisation organisation = this.userService.getOrganisationById(organisationId);
        return new ResponseEntity<>(mapperFacade.map(organisation, OrganisationResource.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/organisations", method = RequestMethod.POST)
    public ResponseEntity<OrganisationResource> createOrganisation(@Valid @RequestBody OrganisationResource organisationResource) {

            Organisation returnOrganisation = userService.addOrganisation(mapperFacade.map(organisationResource, Organisation.class));
            return new ResponseEntity<>(mapperFacade.map(returnOrganisation, OrganisationResource.class), HttpStatus.OK);


    }

    /*@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<UserResource> updateUserById(@PathVariable int userId,
                                                       @Valid @RequestBody PersonResource personResource)
    {
        logger.info(this.getClass().toString() + ":" + "updating user " + userId);

        User user_in = userService.findUserById(userId);
        mapperFacade.map(personResource, user_in.getPerson());
        User user_out = userService.saveUser(user_in);

        return new ResponseEntity<>(userResourceAssembler.toResource(user_out), HttpStatus.CREATED);
    }*/

   /* @RequestMapping(value = "/{userId}/password", method = RequestMethod.PUT)
    public ResponseEntity<UserResource> updatePassword(@PathVariable int userId,
                                                       @Valid @RequestBody UserResource userResource)

    {

        logger.info(this.getClass().toString() + ": updating user " + userId);
        userService.updatePassword(userId, userResource.getOldPassword(), userResource.getPassword());
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(userResourceAssembler.toResource(user), HttpStatus.CREATED);
    }*/

   /* @RequestMapping(value = "/{userId}/repairs", method = RequestMethod.GET)
    public ResponseEntity<List<RepairResource>> getRepairsByUser(@PathVariable Integer userId)
    {
        logger.info(this.getClass().toString() + ":" + "returning for user:" + userId);

        return new ResponseEntity<>(
                repairResourceAssembler.toResources(repairService.findRepairsByUserId(userId)),
                HttpStatus.OK);

    }*/

}
