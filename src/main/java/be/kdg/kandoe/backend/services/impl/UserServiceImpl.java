/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.user.Organisation;
import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.persistence.api.OrganisationRepository;
import be.kdg.kandoe.backend.persistence.api.UserRepository;
import be.kdg.kandoe.backend.services.api.UserService;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Users are stored in map with their unique usernames being keys.
 * A user can be Client or a Repairer.
 *
 * @author wouter
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganisationRepository organisationRepository;

    //Organisation
    @Override
    public Organisation addOrganisation(Organisation organisation) throws UserServiceException {
        if(organisation.getName().isEmpty()) {
            throw new UserServiceException("Empty name");
        }
        if(getOrganisationByName(organisation.getName()) != null) {
            throw new UserServiceException("Duplicate name");
        }

        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation updateOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation getOrganisationById(int id) {
        return organisationRepository.findOne(id);
    }

    @Override
    public Organisation getOrganisationByName(String name) {
        List<Organisation> organisation = organisationRepository.getOrganisationByName(name);
        if(organisation.size() == 0) {
            return null;
        }
        return organisationRepository.getOrganisationByName(name).get(0);
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OrganisationRepository organisationRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.organisationRepository = organisationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieves User by its unique userId
     *
     * @param userId Id of a specific user
     * @return User with this userId
     * @throws UserServiceException if User is not found
     */
    public User findUserById(int userId) throws UserServiceException
    {
        User u = userRepository.findOne(userId);

        if (u == null)
            throw new UserServiceException("User not found");

        return u;
    }

    @Override
    public List<User> findUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public List<Organisation> findOrganisations() {
        return organisationRepository.findAll();
    }

    @Override
    public Organisation addOrganisationWithOrganisator(Organisation organisation, int userId) throws UserServiceException {
        if(organisation.getName().isEmpty()) {
            throw new UserServiceException("Empty name");
        }
        if(getOrganisationByName(organisation.getName()) != null) {
            throw new UserServiceException("Duplicate name");
        }
        User user = findUserById(userId);
        if(user == null) {
            throw new UserServiceException("User is null");
        }
        user.addOrganisation(organisation);
        organisation.setOrganisator(user);
        organisation = organisationRepository.save(organisation);
        user = userRepository.save(user);

        return organisation;
    }


    /**
     * Returns user having username
     *
     * @param username
     * @return
     * @throws UserServiceException
     */
    @Override
    public User findUserByUsername(String username) throws UserServiceException
    {
        User user = userRepository.findUserByUsername(username);

        if (user == null)
            throw new UserServiceException("User not found");

        return user;
    }

    public User saveUser(User user) throws UserServiceException
    {
        User u = userRepository.save(user);
        if (u == null)
            throw new UserServiceException("User not saved");
        return u;
    }

    /**
     * Update an existing user with new Person details
     *
     * @param userId Id of user
     */
    @Override
    public User updateUser(Integer userId) throws UserServiceException
    {
        User u = userRepository.findOne(userId);
        return userRepository.save(u);
    }

    /**
     * Adds new user to the system but first encrypts his/her password.
     * This approach makes User decoupled from PasswordEncoder
     *
     * @param user
     * @throws UserServiceException
     */
    @Override
    public User addUser(User user) throws UserServiceException
    {
        user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
        return this.saveUser(user);
    }

    /**
     * Removes with userId
     *
     * @param userId Id of user object to delete
     */
    @Override
    public void deleteUser(Integer userId) throws UserServiceException
    {
        User user = userRepository.findOne(userId);
        if (user == null)
            throw new UserServiceException("User not found");

        userRepository.delete(user);
    }

    /**
     * Hier wordt gecontroleerd of de user bestaat en of zijn currentPassword correct
     * is. Indien één van deze controles faalt, wordt een UserServiceException gegooid.
     *
     * @param userId          Id of user
     * @param currentPassword current currentPassword
     * @throws UserServiceException when wrong credentials are given
     */
    @Override
    public void checkLogin(Integer userId, String currentPassword) throws UserServiceException
    {
        User u = userRepository.findOne(userId);

        if (u == null || !passwordEncoder.matches(currentPassword, u.getEncryptedPassword()))
        {
            throw new UserServiceException(("Gebruikersnaam of password foutief voor gebruiker " + userId));
        }
    }

    /**
     * Update currentPassword for an existing user
     *
     * @param userId      Id of user
     * @param oldPassword old password
     * @param newPassword new password
     * @throws UserServiceException
     */
    @Override
    public void updatePassword(Integer userId, String oldPassword, String newPassword) throws UserServiceException
    {
        User u = userRepository.findOne(userId);
        checkLogin(userId, oldPassword);
        u.setEncryptedPassword(passwordEncoder.encode(newPassword));
        userRepository.save(u);
    }

    @Override
    public void deleteOrganisation(Integer organisationId) throws UserServiceException {
        Organisation o = organisationRepository.findOne(organisationId);
        User u;
        try {
            u = userRepository.findOne(o.getOrganisator().getId());
        } catch (Exception e) {
            throw new UserServiceException("No organisator found");
        }
        u.removeOrganisation(o);
        userRepository.saveAndFlush(u);
        o.setOrganisator(null);
        organisationRepository.save(o);
        organisationRepository.delete(organisationId);
        organisationRepository.flush();
    }

    /**
     * Implemtation of Spring Security UserDetailsService method
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User u = userRepository.findUserByUsername(username);

        if (u == null) throw new UsernameNotFoundException("No such user: " + username);

        return u;
    }


}