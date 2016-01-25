package com.intuit.contacts.dao;

import java.util.List;

import com.intuit.contacts.entity.Person;
import com.intuit.contacts.model.Contact;

/**
 * @author arjun
 *
 */
public interface IPersonRepository extends IGenericRepository<Person, Integer>{
	
	//abstract method to fetch all contacts
	List<Contact> findAllContacts();


}
