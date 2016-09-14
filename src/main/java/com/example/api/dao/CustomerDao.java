package com.example.api.dao;

import com.example.api.model.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> list();

    Customer create(Customer customer);

    Customer get(Long id);

    Long delete(Long id);

    Customer update(Long id, Customer customer);

}
