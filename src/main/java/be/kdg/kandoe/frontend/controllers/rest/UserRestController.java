package be.kdg.kandoe.frontend.controllers.rest;

import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.frontend.controllers.resources.assemblers.UserResourceAssembler;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResource;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@ExposesResourceFor(UserResource.class)
public class UserRestController
{
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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test()
    {
        return "Cksjf";
    }


/*    @InitBinder("userResource")
    public void initBinder(WebDataBinder webDataBinder)
    {
        webDataBinder.addValidators(new EmailValidator());
    }
    */

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> createUser(@Valid @RequestBody User user)
    {
        User u = userService.addUser(user);
        return new ResponseEntity<>(userResourceAssembler.toResource(user), HttpStatus.OK);
    }

/*    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResource>> findUsers(
            @RequestParam(value = "search", required = false) String criteria)
    {
        List<SearchCriterium> searchCriteria = parseCriteria(criteria);
        List<User> users = userService.findUsersByCriteria(searchCriteria);

        List<UserResource> userResources = users.stream().map(u -> userResourceAssembler.toResource(u)).collect(Collectors.toList());
        return new ResponseEntity<>(userResources, HttpStatus.OK);
    }*/

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> findUserById(@PathVariable int userId)
    {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(new UserResource(user), HttpStatus.OK);
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
