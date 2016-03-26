/**
 * 
 */
package org.me.contacts.service;

import java.util.List;

import org.me.contacts.entity.Person;
import org.me.contacts.model.Contact;

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
