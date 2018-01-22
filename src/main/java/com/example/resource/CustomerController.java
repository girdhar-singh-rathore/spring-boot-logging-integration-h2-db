package com.example.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootH2DbWebAppApplication;
import com.example.domain.Customer;
import com.example.service.CustomerService;

@RestController
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootH2DbWebAppApplication.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping("customers")
	public List<Customer> getAllCustomer() {
		logger.debug("getting all customers..");
		return customerService.listAll();
	}

	@PostMapping("customers")
	public void addCustomer(@RequestBody Customer customer) {
		logger.debug("Customer saved: {}", customer);
		customerService.saveOrUpdate(customer);
	}

	@PutMapping("customers")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customerDetails) {
		Customer customer = customerService.getById(customerDetails.getId());
		if (customer == null) {
			logger.debug("customer is not available: {}", customerDetails);
			return ResponseEntity.notFound().build();
		}
		Customer updatedCustomer = customerService.saveOrUpdate(customerDetails);
		logger.debug("customer updated: {}",  updatedCustomer);
		return ResponseEntity.ok(updatedCustomer);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") String id) {
		Customer customer = customerService.getById(id);
		logger.debug("getting customer with id: {}", id);
		if (customer == null) {
			logger.debug("customer not available with id: {}", id);
			return ResponseEntity.notFound().build();
		}
		logger.debug("getting customer by id: customer details is: {}", customer);
		return ResponseEntity.ok().body(customer);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") String id) {
		logger.debug("deleting customer having id: {}",  id);
		Customer customer = customerService.getById(id);
		if (customer == null) {
			logger.debug("customer with id not avaible: {}", id);
			return ResponseEntity.notFound().build();
		}
		customerService.delete(id);
		logger.debug("customer deleted successfully: {}",customer);
		return ResponseEntity.ok().build();
	}
}
