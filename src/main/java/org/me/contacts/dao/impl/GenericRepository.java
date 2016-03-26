package org.me.contacts.dao.impl;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.me.contacts.dao.IGenericRepository;

/**
 * Generically implements basic DAO functionality specified by IGenericDAO.
 * <p>
 * All the concrete DAOs under this same package inherit from this class. 
 * They also implement the corresponding DAO interface.
 * 
 * @param <T> type of the entity bean / data model object the DAO is for
 * @author arjun
 *
 */

public abstract class GenericRepository<T,  PK extends Serializable> implements IGenericRepository<T, PK> {

    /**
     * <code>clazz</code> represents the entity on which data access operations are to be performed
     */
    private final Class<?> clazz;

    /**
     * JPA's EntityManager through which all data access operation are done
     */
    @PersistenceContext
    protected EntityManager em;

    /**
     * Checks if session factory is available when an DAO instance is created. If no entity manager is
     * present an <code>IllegalStateException</code> is raised.
     */
    @PostConstruct
    public void init() {

        if (em == null) {
            throw new IllegalStateException("EntityManager cannot be null");
        }

    }

    /**
     * setter method to enable spring injection of JPA
     * 
     * @param EntityManager
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected GenericRepository(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * Method to return Entity clazz
     * 
     * @return entity as clazz
     */
    protected Class<?> getPersistentClass() {
        return clazz;
    }

    /**
     * Fetch entity by id.
     */
    @SuppressWarnings("unchecked")
    public T get(PK identifier) {
        return (T) em.find(this.getPersistentClass(), identifier);
    }

    /**
     * Delete a entity by identifier.
     */
    public void remove(PK identifier) {
        this.remove(this.get(identifier));
    }

    /**
     * Delete a entity.
     */
    public void remove(T object) {
        em.remove(object);
    }

    /**
     * Update a entity.
     */
    public T update(T object) {
       return em.merge(object);
    }

    /**
     * Create a entity.
     */
    public void create(T object) {
        em.persist(object);
    }

}