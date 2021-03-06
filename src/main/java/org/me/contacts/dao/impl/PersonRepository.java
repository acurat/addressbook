package org.me.contacts.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.me.contacts.dao.IPersonRepository;
import org.me.contacts.entity.Person;
import org.me.contacts.model.Contact;
import org.springframework.stereotype.Repository;

/**
 * @author arjun
 *
 */
@Repository
public class PersonRepository extends GenericRepository<Person, Integer> implements IPersonRepository {

	protected PersonRepository() {
		super(Person.class);
	}
	

	public List<Contact> findAllContacts() {		
		Query query = em.createNamedQuery("Person.findAllNames");
		
		List<Contact> contacts = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		Contact contact;
		
		for(Object[] obj: results){
			contact = new Contact();
			contact.setContactId((Integer) obj[0]);
			contact.setLastName((String) obj[1]);
			contact.setFirstName((String) obj[2]);
			contact.setPhoneNumber((String) obj[3]);

			contacts.add(contact);
		}
		 
		 return contacts;
	}

}
