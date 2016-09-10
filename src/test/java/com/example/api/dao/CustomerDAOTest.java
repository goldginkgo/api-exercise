package com.example.api.dao;

import com.example.api.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDAOTest {
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    private CustomerDAO customerDao;

    @Before
    public void setUp() throws Exception {
        customer1 = new Customer(101, "Lily", "12 Queen Street", "021-123-4567");
        customer2 = new Customer(102, "Jack", "11 Lake Road", "021-234-5678");
        customer3 = new Customer(103, "Jerry", "12 Lake Road", "027-345-6789");

        Database database = new Database();
        customerDao = new CustomerDAO(database);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void list() throws Exception {
        assertEquals(0, customerDao.list().size());
    }

    @Test
    public void create() throws Exception {
        assertEquals(0, customerDao.list().size());
        customerDao.create(customer1);
        assertEquals(1, customerDao.list().size());
    }

    @Test
    public void get() throws Exception {
        Customer customerCreated = customerDao.create(customer1);
        Customer customerGet = customerDao.get((long) 1);
        assertEquals(customerCreated, customerGet);
    }

    @Test
    public void getNullCustomer() throws Exception {
        Customer customer = customerDao.get((long) 2);
        assertNull(customer);
    }

    @Test
    public void delete() throws Exception {
        customerDao.create(customer1);
        customerDao.create(customer2);
        customerDao.create(customer3);
        assertEquals(3, customerDao.list().size());
        long id = customerDao.delete((long) 2);
        assertEquals(2, id);
        assertEquals(2, customerDao.list().size());
    }

    @Test
    public void deleteNullCustomer() throws Exception {
        Long id = customerDao.delete((long) 2);
        assertNull(id);
    }

    @Test
    public void update() throws Exception {
        customerDao.create(customer1);
        customerDao.update((long) 1, customer2);
        Customer customer = customerDao.get((long) 1);
        assertEquals(customer2.getName(), customer.getName());
        assertEquals(customer2.getAddress(), customer.getAddress());
        assertEquals(customer2.getTelephone(), customer.getTelephone());
    }

    @Test
    public void updateNullCustomer() throws Exception {
        Customer customer = customerDao.update((long) 1, customer2);
        assertNull(customer);
    }
}