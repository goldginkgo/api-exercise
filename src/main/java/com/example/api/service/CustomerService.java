package com.example.api.service;

import com.example.api.dao.CustomerDaoImpl;
import com.example.api.model.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDaoImpl getCustomerDAO();

    List<Customer> list();

    Customer create(Customer customer);

    Customer get(Long id);

    Long delete(Long id);

    Customer update(Long id, Customer customer);

}
