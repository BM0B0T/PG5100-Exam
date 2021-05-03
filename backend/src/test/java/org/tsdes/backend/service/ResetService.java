package org.tsdes.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.Movie;
import org.tsdes.backend.entity.Review;
import org.tsdes.backend.entity.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
@Service
@Transactional
public class ResetService {

    @PersistenceContext
    private EntityManager em;

    public void resetDatabase() {
        deleteEntities(User.class);
        deleteEntities(Review.class);
        deleteEntities(Movie.class);
    }

    private void deleteEntities(Class <?> entity) {

        if(entity == null || entity.getAnnotation(Entity.class) == null){
            throw new IllegalArgumentException("Invalid non-entity class");
        }
        String name = entity.getSimpleName();
        Query query = em.createQuery("delete from " + name);
        query.executeUpdate();
    }

}
