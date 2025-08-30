package ir.mehdihosseini.basicframework.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

class BasicEntityManager {
    @PersistenceContext
    private EntityManager entityManager;

    protected BasicEntityManager() {
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

}
