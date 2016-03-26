package org.me.contacts.dao;

import java.util.List;

import org.me.contacts.entity.Person;
import org.me.contacts.model.Contact;

/**
 * @author arjun
 *
 */
public interface IPersonRepository extends IGenericRepository<Person, Integer>{
	
	//abstract method to fetch all contacts
	List<Contact> findAllContacts();


}
