package com.earl.UsingJsonPatch;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerServiceI {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> finalAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> findCustomer(String id) {
		return customerRepository.findById(id);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}
