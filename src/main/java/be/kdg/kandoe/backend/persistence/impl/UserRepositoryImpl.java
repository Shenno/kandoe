/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.kandoe.backend.persistence.impl;

import be.kdg.kandoe.backend.dom.user.User;
import be.kdg.kandoe.backend.persistence.api.UserRepositoryCustom;
import be.kdg.kandoe.backend.services.exceptions.UserServiceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation for a {link: UserRepository}
 */

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepositoryCustom
{
    @PersistenceContext
    private EntityManager em;

    @Override
    // TODO: Replace UserServiceException by new UserDAOExceptions or something
    public Integer addUser(User user) throws UserServiceException
    {
        final TypedQuery<User> q = em.createNamedQuery("User.findByUsername", User.class);
        q.setParameter("username", user.getUsername());
        if (!q.getResultList().isEmpty())
        {
            throw new UserServiceException("User " + user.getUsername() + " already exists");
        }
        final Session session = em.unwrap(Session.class);
        session.saveOrUpdate(user);
        return user.getId();
    }

    @Override
    public List<String> findAllUsernames() {
        final TypedQuery<String> q = em.createNamedQuery("User.findAllUsernames", String.class);
        return q.getResultList();
    }
}
