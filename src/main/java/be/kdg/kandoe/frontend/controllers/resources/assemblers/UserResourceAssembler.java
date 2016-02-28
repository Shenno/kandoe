/*package be.kdg.kandoe.frontend.controllers.resources.assemblers;

import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResource;
import be.kdg.kandoe.frontend.controllers.rest.UserRestController;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource>
{
    private Logger logger = Logger.getLogger(UserResourceAssembler.class);

    @Autowired
    private MapperFacade mapper;

    public UserResourceAssembler()
    {
        super(UserRestController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user)
    {
        // map Entity class to Resource class using Orika Framework
        UserResource userResource = mapper.map(user, UserResource.class);

        // add HATEOAS stuff to mapped Resource
        // userResource.add(linkTo(methodOn(UserRestController.class).findUserById(user.getId())).withSelfRel());

        return userResource;
    }
}
*/