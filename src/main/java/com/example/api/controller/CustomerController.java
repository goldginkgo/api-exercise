package com.example.api.controller;

import java.util.List;

import com.example.api.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Customer;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private static final Logger logger = LogManager.getLogger(CustomerController.class.getName());

    @PostMapping(value = "/customers")
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.create(customer);
        if ( null == newCustomer) {
            logger.error("Creating Customer (" + customer + ") failed. Customer already exists or bad request.");
            return new ResponseEntity<>("Creating Customer failed. Customer already exists or bad request.",
                                        HttpStatus.BAD_REQUEST);
        } else {
            logger.info("Customer (" + customer + ") created successfully.");
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity getCustomers() {
        List<Customer> customers = customerService.list();
        logger.info("Customers " + customers + " retrieved successfully.");
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.get(id);
        if (customer == null) {
            logger.error("Getting Customer failed. No Customer found for ID " + id);
            return new ResponseEntity<>("Getting Customer failed. No Customer found for ID " + id,
                                        HttpStatus.NOT_FOUND);
        }

        logger.info("Customer (" + customer + ") retrieved successfully.");
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        if (null == customerService.delete(id)) {
            logger.error("Deleting Customer failed. No Customer found for ID " + id);
            return new ResponseEntity<>("Deleting Customer failed. No Customer found for ID " + id,
                                        HttpStatus.NOT_FOUND);
        }

        logger.info("Customer deleted successfully. Customer ID=" + id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer = customerService.update(id, customer);

        if (null == customer) {
            logger.error("Updating Customer failed. Bad request or no Customer found for ID " + id);
            return new ResponseEntity<>("Deleting Customer failed. Bad request or no Customer found for ID " + id,
                                        HttpStatus.BAD_REQUEST);
        }

        logger.info("Customer updated successfully. CustomerID=" + id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
