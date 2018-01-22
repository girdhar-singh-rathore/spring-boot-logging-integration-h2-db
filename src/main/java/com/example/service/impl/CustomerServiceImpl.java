package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> listAll() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}

	@Override
	public Customer getById(String id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer saveOrUpdate(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public void delete(String id) {
		customerRepository.delete(id);
	}

}