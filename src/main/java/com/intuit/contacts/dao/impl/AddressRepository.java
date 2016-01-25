/**
 * 
 */
package com.intuit.contacts.dao.impl;

import org.springframework.stereotype.Repository;

import com.intuit.contacts.dao.IAddressRepository;
import com.intuit.contacts.entity.Address;

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
