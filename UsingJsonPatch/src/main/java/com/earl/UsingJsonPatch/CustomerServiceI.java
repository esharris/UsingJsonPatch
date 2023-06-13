package com.earl.UsingJsonPatch;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceI {

	List<Customer> finalAllCustomers();

	Optional<Customer> findCustomer(String id);

	Customer saveCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

}