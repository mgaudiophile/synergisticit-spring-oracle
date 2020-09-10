package com.synergisticit.repository;

import java.util.List;

import com.synergisticit.domain.Customer;

public interface CustomerRepository {

	List<Customer> findAll();
	Customer save(Customer customer);
	Customer update(Customer customer);
	void delete(int customer_id, String first_name, String last_name);
}
