package org.me.contacts.uiservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.me.contacts.entity.Person;
import org.me.contacts.model.Contact;
import org.me.contacts.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arjun
 * 
 * Rest controller implementing all API end points
 * 
 */
@RestController
@RequestMapping("/api")
public class ApiController {

	private static final Logger logger = Logger.getLogger(ApiController.class);

	@Autowired
	private IPersonService personService;
	
	
	/**
	 * GET method - for all contacts
	 * 
	 */
	@RequestMapping(value = "/contacts", method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Contact> allContacts() {
		logger.info("Get all contacts");
		return personService.findAllContacts();
	}
	
	
	/**
	 *  POST method - add a new contact 
	 *  
	 */
	@RequestMapping(value = "/contacts", method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addContact(@RequestBody Person person) {
		logger.info("Create new contact : last name " + person.getLastName());
		personService.addPerson(person);
		logger.info("Created new contact : id: " + person.getId());
		return new ResponseEntity<>(new Contact(person.getId()), new HttpHeaders(), HttpStatus.CREATED);
	}

	/** 
	 * PUT method - to edit a contact 
	 * 
	 */
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void editContact(@RequestBody Person person, @PathVariable("id") Integer id) {
		logger.info("Save contact with id " + person.getId());
		personService.editPerson(person);

	}

	/** 
	 * DELETE method - to delete contact 
	 * 
	 */
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
	public void deleteContact(@PathVariable("id") Integer id) {
		logger.info("Delete Contact with Id: " + id);
		personService.deletePerson(id);

	}

	/**
	 * 
	 *  GET method - fetch a contact by id
	 *  
	 */
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Person getContact(@PathVariable("id") Integer id) {
		logger.info("Get Contact with Id: " + id);
		return personService.findPerson(id);
	}


}
