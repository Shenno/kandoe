package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.frontend.controllers.resources.users.OrganisationResource;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResourceRegister;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Created by Kevin on 3/03/2016.
 */
@Component
public class UserRegisterMapper extends CustomMapper<User, UserResourceRegister> {
    @Override
    public void mapBtoA(UserResourceRegister userResourceRegister, User user, MappingContext context) {
        user.setEncryptedPassword(userResourceRegister.getPassword());
        user.setFirstName(userResourceRegister.getFirstName());
        user.setLastName(userResourceRegister.getLastName());
        user.setUsername(userResourceRegister.getUsername());
    }

    @Override
    public void mapAtoB(User user, UserResourceRegister userResourceRegister, MappingContext context) {
        super.mapAtoB(user, userResourceRegister, context);
    }
}
