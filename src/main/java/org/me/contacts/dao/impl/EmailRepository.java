package org.me.contacts.dao.impl;

import org.me.contacts.dao.IEmailRepository;
import org.me.contacts.entity.Email;
import org.springframework.stereotype.Repository;

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
