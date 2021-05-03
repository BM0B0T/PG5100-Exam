package org.tsdes.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public boolean createUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        if(em.find(UserEntity.class, username) != null)
            return false;
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        em.persist(user);
        return true;
    }
}
