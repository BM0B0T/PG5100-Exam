package org.tsdes.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.User;

import javax.persistence.EntityManager;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String username, String password, String firstName, String lastName) {
        String hashedPassword = passwordEncoder.encode(password);
        if(em.find(User.class, username) != null)
            return false;
        if(password.length() <= 6)
            return false;

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        em.persist(user);
        return true;
    }

    public User getUser(String username) {
        return em.find(User.class, username);
    }
}
