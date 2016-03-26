package org.me.contacts.service.impl;

import java.util.List;

import org.me.contacts.dao.IAddressRepository;
import org.me.contacts.dao.IEmailRepository;
import org.me.contacts.dao.IPersonRepository;
import org.me.contacts.dao.IPhoneRepository;
import org.me.contacts.entity.Address;
import org.me.contacts.entity.Email;
import org.me.contacts.entity.Person;
import org.me.contacts.entity.Phone;
import org.me.contacts.model.Contact;
import org.me.contacts.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author arjun
 *
 */

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements IPersonService {
	
	//Autowire all dependencies using Spring
	
	@Autowired
	private IPersonRepository personRepository;
	
	@Autowired
	private IAddressRepository addressRepository;
	
	@Autowired
	private IPhoneRepository phoneRepository;
	
	@Autowired
	private IEmailRepository emailRepository;
	
	/**
	 * Fetching all contacts from data layer
	 * 
	 */
	@Override
	public List<Contact> findAllContacts() {
		return personRepository.findAllContacts();
	}

	/**
	 * Adding a new contact
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public void addPerson(Person person) {
		
		if(person.getAddress() == null){
			person.setAddress(new Address());
		}
		person.getAddress().setPerson(person);

		if(person.getEmails()!=null){
			for(Email email : person.getEmails()){
				email.setPerson(person);
			}
		}
		if(person.getPhones()!=null){
			for(Phone phone : person.getPhones()){
				phone.setPerson(person);
			}
		}
		personRepository.create(person);
	}

	/**
	 * Editing a contact
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public void editPerson(Person person) {

		Person p = personRepository.update(person);

		if(person.getAddress()!=null){
			person.getAddress().setPerson(p);
			addressRepository.update(person.getAddress());
		}
		
		if(person.getPhones() != null){
		for(Phone ph: person.getPhones()){
			ph.setPerson(p);
			phoneRepository.update(ph);
		}
		}
		
		if(person.getEmails() != null){
		for(Email e: person.getEmails()){
			e.setPerson(p);
			emailRepository.update(e);
		}
		}
		
	}

	/**
	 * Delete contact (Not used in this demo)
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public void deletePerson(Integer id) {
		personRepository.remove(id);		
	}
	
	/**
	 * Find contact using Id
	 * 
	 */
	@Override
	public Person findPerson(Integer id) {		
		return  personRepository.get(id);
	}
	
	

}
