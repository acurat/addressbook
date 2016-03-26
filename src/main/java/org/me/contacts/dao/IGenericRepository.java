package org.me.contacts.dao;

import java.io.Serializable;


/**
 * Generic interface for a DAO . Defines minimal functionality a DAO must implement.
 * <p>
 *  Specific DAO interfaces implement this interface, possibly adding some new functionality.
 * 
 * @param <T> type of the data model object the DAO is for
 * @author arjun
 */
public interface IGenericRepository<T, PK extends Serializable> {

    /**
     * Fetch entity by id.
     * 
     * @param identifier
     * @return entity if found or null if not found
     */
    T get(PK identifier);

    /**
     * Delete an entity by identifier.
     * 
     * @param identifier
     */
    void remove(PK identifier);

    /**
     * Delete an entity by object.
     * @param object 
     * 
     * @param entity instance to remove
     */
    void remove(T object);

    /**
     * Update an entity.
     * @param object 
     * 
     * @param entity instance to store
     */
    T update(T object);

    /**
     * Create an entity.
     * @param object 
     * 
     * @param entity instance to create
     * 
     */
    void create(T object);
}