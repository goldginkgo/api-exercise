package com.example.api.service;

import com.example.api.dao.CustomerDaoImpl;
import com.example.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDaoImpl customerDao;

    public CustomerDaoImpl getCustomerDAO() {
        return customerDao;
    }

    public List<Customer> list(){
        return getCustomerDAO().list();
    }


    public Customer create(Customer customer){
        return getCustomerDAO().create(customer);
    }

    public Customer get(Long id){
        return getCustomerDAO().get(id);
    }

    public Long delete(Long id){
        return getCustomerDAO().delete(id);
    }

    public Customer update(Long id, Customer customer){
        return getCustomerDAO().update(id, customer);
    }
}
