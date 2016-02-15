package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.users.User;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;

import java.util.List;

/**
 * Extra interface to support customization of Spring's Data interfaces
 */
public interface UserRepositoryCustom
{
    // https://programmeren3-repaircafe.rhcloud.com/repair-cafe-applicatie/repair-cafe-backend/backend-persistence-layer/

    Integer addUser(User user) throws UserServiceException;
}
