package com.example.api.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.example.api.model.Customer;

@Component
public class CustomerDAO {
    private Database database;
    private final AtomicLong counter = new AtomicLong();

    public CustomerDAO(Database database) {
        this.database = database;
    }

    /**
     * Returns list of customers from dummy database.
     *
     * @return list of customers
     */
    public List<Customer> list() {
        return this.database.getCustomers();
    }

    /**
     * Create new customer in dummy database. Updates the id and insert new
     * customer in list.
     *
     * @param customer
     *            Customer object
     * @return customer object with updated id
     */
    public Customer create(Customer customer) {
        if (customer.getName() == null || customer.getAddress() == null || customer.getTelephone() == null) {
            return null;
        }
        for (Customer c : this.database.getCustomers()) {
            if (c.equals(customer)) {
                return null;
            }
        }
        customer.setId(counter.incrementAndGet());
        this.database.getCustomers().add(customer);
        return customer;
    }

    /**
     * Return customer object for given id from dummy database. If customer is
     * not found for id, returns null.
     *
     * @param id
     *            customer id
     * @return customer object for given id
     */
    public Customer get(Long id) {
        for (Customer c : this.database.getCustomers()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Delete the customer object from dummy database. If customer not found for
     * given id, returns null.
     *
     * @param id
     *            the customer id
     * @return id of deleted customer object
     */
    public Long delete(Long id) {
        for (Customer c :  this.database.getCustomers()) {
            if (c.getId().equals(id)) {
                this.database.getCustomers().remove(c);
                return id;
            }
        }

        return null;
    }

    /**
     * Update the customer object for given id in dummy database. If customer
     * not exists, returns null
     *
     * @param id
     * @param customer
     * @return customer object with id
     */
    public Customer update(Long id, Customer customer) {
        if (customer.getName() == null || customer.getAddress() == null || customer.getTelephone() == null) {
            return null;
        }
        for (Customer c :  this.database.getCustomers()) {
            if (c.getId().equals(id)) {
                customer.setId(c.getId());
                this.database.getCustomers().remove(c);
                this.database.getCustomers().add(customer);
                return customer;
            }
        }

        return null;
    }

}
