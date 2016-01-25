package com.intuit.contacts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the Phone database table.
 * 
 */
@Entity
@NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@Column(name = "Number")
	private String number;

	@Column(name = "Type")
	private String type;

	@Column(name = "PrimaryPhone", columnDefinition="bit")
	private Boolean primaryPhone;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "PersonID")
	@JsonIgnore
	private Person person;

	public Phone() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the primary
	 */
	public Boolean getPrimaryPhone() {
		return primaryPhone;
	}

	/**
	 * @param primary
	 *            the primary to set
	 */
	public void setPrimaryPhone(Boolean primary) {
		this.primaryPhone = primary;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

}