/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


/**
 * @author wouter
 */
public interface UserService extends UserDetailsService
{
    // Organisation
    Organisation addOrganisation(Organisation organisation);
    Organisation getOrganisationById(int id);
    Organisation getOrganisationByName(String name);

    User findUserById(int id) throws UserServiceException;

    List<User> findUsers();

    User findUserByUsername(String username) throws UserServiceException;

    User saveUser(User user) throws UserServiceException;

    User updateUser(Integer userId) throws UserServiceException;

    User addUser(User user) throws UserServiceException;

    void deleteUser(Integer userId) throws UserServiceException;

    void checkLogin(Integer userId, String password) throws UserServiceException;

    void updatePassword(Integer userId, String oldPassword, String newPassword) throws UserServiceException;

    void deleteOrganisation(Integer organisationId);
}
