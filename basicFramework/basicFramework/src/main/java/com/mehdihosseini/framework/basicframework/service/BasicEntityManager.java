package com.mehdihosseini.framework.basicframework.service;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
