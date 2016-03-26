package org.me.contacts.entity;

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
 * The persistent class for the Email database table.
 * 
 */
@Entity
@NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@Column(name = "Address")
	private String address;

	@Column(name = "PrimaryEmail", columnDefinition="bit")
	private Boolean primaryEmail;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "PersonID")
	@JsonIgnore
	private Person person;

	public Email() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the primary
	 */
	public Boolean getPrimaryEmail() {
		return primaryEmail;
	}

	/**
	 * @param primary
	 *            the primary to set
	 */
	public void setPrimaryEmail(Boolean primary) {
		this.primaryEmail = primary;
	}

}