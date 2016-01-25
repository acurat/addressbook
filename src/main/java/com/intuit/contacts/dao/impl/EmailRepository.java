package com.intuit.contacts.dao.impl;

import org.springframework.stereotype.Repository;

import com.intuit.contacts.dao.IEmailRepository;
import com.intuit.contacts.entity.Email;

/**
 * @author arjun
 *
 */
@Repository
public class EmailRepository extends GenericRepository<Email, Integer> implements IEmailRepository {


	protected EmailRepository() {
		super(Email.class);
	}
}
