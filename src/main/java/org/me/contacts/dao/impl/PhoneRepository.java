/**
 * 
 */
package org.me.contacts.dao.impl;

import org.me.contacts.dao.IPhoneRepository;
import org.me.contacts.entity.Phone;
import org.springframework.stereotype.Repository;

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
