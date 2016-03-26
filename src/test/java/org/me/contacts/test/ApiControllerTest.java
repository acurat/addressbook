package org.me.contacts.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.me.contacts.entity.Address;
import org.me.contacts.entity.Email;
import org.me.contacts.entity.Person;
import org.me.contacts.entity.Phone;
import org.me.contacts.uiservice.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;


/**
 * JUNIT test class for testing the API REST endpoints
 * @author arjun
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/config/spring-servlet.xml",
		"file:src/main/webapp/WEB-INF/config/ds-config.xml" })
// @Ignore //Ignoring
public class ApiControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	private static final Logger logger = Logger.getLogger(ApiController.class);

	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
	}

	// JUNIT test cases for REST Methods

	// GET All Contacts
	@Test
	public void getAllContactTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/contacts")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		logger.info(result.getResponse().getContentAsString());

	}

	// GET 1 Contact
	@Test
	public void getContactTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/contacts/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		logger.info(result.getResponse().getContentAsString());

	}

	// POST 1 Contact
	@Test
	public void addContactTest() throws Exception {

		Person person = new Person();
		person.setLastName("Tester");
		person.setFirstName("Junit");

		// Add Phone
		Phone phone = new Phone();
		phone.setNumber("4255059999");
		phone.setType("Cell");
		phone.setPrimaryPhone(true);
		person.addPhone(phone);

		// Add Address
		Address add = new Address();
		add.setAddress1("Test");
		add.setAddress2("Test");
		add.setCity("San Diego");
		person.setAddress(add);

		// Add Email
		Email email1 = new Email();
		email1.setAddress("abc@com");
		email1.setPrimaryEmail(true);
		person.addEmail(email1);

		// Add Email 2
		Email email2 = new Email();
		email2.setAddress("xyz@com");
		person.addEmail(email2);

		Gson gson = new Gson();
		String json = gson.toJson(person);
		
		logger.info(json);

		mockMvc.perform(post("/api/contacts").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().isCreated());
	}

	// PUT - Editing a contact
	@Test
	public void editContactTest() throws Exception {

		Person person = new Person();
		person.setId(1);
		person.setFirstName("HTTP PUT");
		person.setLastName("JUNIT MODIFIER");
		
		
		Address add = new Address();
		add.setId(1);
		add.setAddress1("test address");
		add.setState("Philly");
		
		person.setAddress(add);
		
		Email email1 = new Email();
		email1.setId(1);
		email1.setAddress("pqr@com");
		email1.setPrimaryEmail(true);
		person.addEmail(email1);
		
		Phone phone = new Phone();
		phone.setId(1);
		phone.setNumber("4255050000");
		phone.setType("Cell");
		phone.setPrimaryPhone(true);
		person.addPhone(phone);

		Gson gson = new Gson();
		String json = gson.toJson(person);
		
		logger.info(json);

		mockMvc.perform(put("/api/contacts/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().isOk());
	}

}