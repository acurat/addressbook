/**
 * 
 */
package com.intuit.contacts.service;

import java.util.List;

import com.intuit.contacts.entity.Person;
import com.intuit.contacts.model.Contact;

/**
 * @author arjun
 *
 */
public interface IPersonService {
	
	public List<Contact> findAllContacts();

	public void addPerson(Person person);
	public void editPerson(Person person);
	public void deletePerson(Integer id);
	public Person findPerson(Integer id);


}
