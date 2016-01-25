package com.intuit.contacts.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the Person database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
		@NamedQuery(name = "Person.findAllNames", query = "SELECT p.id, p.lastName, p.firstName, ph.number FROM Person as p "
				+ "INNER JOIN p.phones as ph WHERE ph.primaryPhone = 1 ORDER BY p.lastName") })
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;
	
	//bi-directional many-to-one association to Email
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Email> emails;

	// bi-directional many-to-one association to Phone
	@OneToMany(mappedBy="person", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Phone> phones;

	
	// bi-directional one-to-one association to Address
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Address address;
	

	public Person() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public void addEmail(Email email) {
		
		if(email != null){
			if(emails == null){
				emails = new ArrayList<Email>();
			}
			
			getEmails().add(email);
		}
	}

	public Email removeEmail(Email email) {
		getEmails().remove(email);
		return email;
	}
	
	
	public List<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone phone) {		
		if(phone != null){
			if(phones == null){
				phones = new ArrayList<Phone>();
			}			
			getPhones().add(phone);
		}
	}

	public Phone removePhone(Phone phone) {
		getPhones().remove(phone);
		return phone;
	}


	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}