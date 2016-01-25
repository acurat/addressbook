/**
 * 
 */
package com.intuit.contacts.dao.impl;

import org.springframework.stereotype.Repository;

import com.intuit.contacts.dao.IPhoneRepository;
import com.intuit.contacts.entity.Phone;

/**
 * @author arjun
 *
 */
@Repository
public class PhoneRepository extends GenericRepository<Phone, Integer> implements IPhoneRepository {
	
	protected PhoneRepository() {
		super(Phone.class);
	}

}
