package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.assemblers.UserResourceAssembler;
import be.kdg.kandoe.frontend.controllers.resources.users.OrganisationResource;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResource;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResourcePost;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@ExposesResourceFor(UserResource.class)
public class UserRestController
{
    @Autowired
    PasswordEncoder encoder;
    private final Logger logger = Logger.getLogger(UserRestController.class);
    private final UserService userService;
    private final MapperFacade mapperFacade;
    private final UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserRestController(UserService userService,
                              MapperFacade mapperFacade, UserResourceAssembler userResourceAssembler
                              )
    {
        this.userService = userService;
        this.mapperFacade = mapperFacade;
        this.userResourceAssembler = userResourceAssembler;
    }

    @RequestMapping(value = "/users/info", method = RequestMethod.GET)
    public String test()
    {
        return "This is the API to handle UserController";
    }


/*    @InitBinder("userResource")
    public void initBinder(WebDataBinder webDataBinder)
    {
        webDataBinder.addValidators(new EmailValidator());
    }
    */

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<UserResource> createUser(@Valid @RequestBody UserResourcePost userResourcePost)
    {
        String pwd = encoder.encode(userResourcePost.getPassword());
        User user = new User(userResourcePost.getUsername(), pwd);
        User returnUser = userService.addUser(user);
        return new ResponseEntity<>(new UserResource(returnUser), HttpStatus.OK);
    }

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
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findUsers()
    {
        List<User> users = userService.findUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> findUserById(@PathVariable int userId)
    {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(new UserResource(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/organisations", method = RequestMethod.GET)
    public ResponseEntity<OrganisationResource> findorganisations()
    {
        OrganisationResource organisationResource = new OrganisationResource();
        organisationResource.setOrganisationId(1);
        organisationResource.setOrganisationName("ShennoOrga");
        List<String> list = new ArrayList<>();
        list.add("lol"); list.add("top");
        organisationResource.setThemes(list);
        return new ResponseEntity<>(organisationResource, HttpStatus.OK);
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
