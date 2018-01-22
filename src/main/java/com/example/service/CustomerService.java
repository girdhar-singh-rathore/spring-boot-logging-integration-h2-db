package com.example.service;

import java.util.List;

import com.example.domain.Customer;

public interface CustomerService {

	List<Customer> listAll();

	Customer getById(String id);

	Customer saveOrUpdate(Customer customr);

	void delete(String id);

}