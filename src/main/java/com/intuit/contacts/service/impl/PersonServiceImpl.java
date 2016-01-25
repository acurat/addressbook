package com.intuit.contacts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.contacts.dao.IAddressRepository;
import com.intuit.contacts.dao.IEmailRepository;
import com.intuit.contacts.dao.IPersonRepository;
import com.intuit.contacts.dao.IPhoneRepository;
import com.intuit.contacts.entity.Address;
import com.intuit.contacts.entity.Email;
import com.intuit.contacts.entity.Person;
import com.intuit.contacts.entity.Phone;
import com.intuit.contacts.model.Contact;
import com.intuit.contacts.service.IPersonService;

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
