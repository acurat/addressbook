/**
 * 
 */
package org.me.contacts.dao.impl;

import org.me.contacts.dao.IAddressRepository;
import org.me.contacts.entity.Address;
import org.springframework.stereotype.Repository;

/**
 * @author arjun
 *
 */
@Repository
public class AddressRepository extends GenericRepository<Address, Integer> implements IAddressRepository {
	
	protected AddressRepository() {
		super(Address.class);
	}

}
