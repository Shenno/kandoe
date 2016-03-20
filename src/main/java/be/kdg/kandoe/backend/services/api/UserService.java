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
 * A UserService is an Interface for the following classes:
 * {link: User}, {link: Organisation}
 */
public interface UserService extends UserDetailsService
{
    // Organisation
    Organisation addOrganisation(Organisation organisation);
    Organisation updateOrganisation(Organisation organisation);
    Organisation getOrganisationById(int id);
    Organisation getOrganisationByName(String name);
    List<Organisation> findOrganisations();
 //   Organisation addOrganisationWithOrganisator(Organisation organisation, int userId);

    User findUserById(int id) throws UserServiceException;

    List<String> findUsernames();

    List<User> findUsers();

  //  List<User> findUsersByOrganisation(int orgId) throws UserServiceException;

    User findUserByUsername(String username) throws UserServiceException;

    User saveUser(User user) throws UserServiceException;

    User updateUser(Integer userId) throws UserServiceException;

    User addUser(User user) throws UserServiceException;

    void deleteUser(Integer userId) throws UserServiceException;

    void checkLogin(Integer userId, String password) throws UserServiceException;

    void updatePassword(Integer userId, String oldPassword, String newPassword) throws UserServiceException;

    void deleteOrganisation(Integer organisationId);
}
