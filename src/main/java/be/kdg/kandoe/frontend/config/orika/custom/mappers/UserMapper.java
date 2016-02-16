package be.kdg.kandoe.frontend.config.orika.custom.mappers;

import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.frontend.controllers.resources.users.UserResource;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends CustomMapper<User, UserResource>
{
    // https://programmeren3-repaircafe.rhcloud.com/repair-cafe-applicatie/repair-cafe-frontend/presentation-layer/orika/

    @Override
    public void mapAtoB(User source, UserResource destination, MappingContext context)
    {
       // mapperFacade.map(source.getPerson(), destination.getPersonResource());
        //mapperFacade.map(source.getPerson().getAddress(), destination.getPersonResource().getAddressResource());
    }

    @Override
    public void mapBtoA(UserResource source, User destination, MappingContext context)
    {
       /* if (source.getRoleTypes() != null)
            destination.setRoles(source.getRoleTypes().stream().map(roleType -> Role.toRole(roleType)).collect(Collectors.toList()));

        if (source.getPassword() != null) destination.setEncryptedPassword(source.getPassword());
        mapperFacade.map(source.getPersonResource(), destination.getPerson());
        mapperFacade.map(source.getPersonResource().getAddressResource(), destination.getPerson().getAddress());
        */
    }

}
