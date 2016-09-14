package com.example.api.dao;

import com.example.api.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDaoTest {
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    private CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        customer1 = new Customer(101, "Lily", "12 Queen Street", "021-123-4567");
        customer2 = new Customer(102, "Jack", "11 Lake Road", "021-234-5678");
        customer3 = new Customer(103, "Jerry", "12 Lake Road", "027-345-6789");
        customerDao = new CustomerDaoImpl(new Database());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void list() throws Exception {
        assertEquals(0, customerDao.list().size());
    }

    @Test
    public void createCustomerSuccessful() throws Exception {
        assertEquals(0, customerDao.list().size());
        customerDao.create(customer1);
        assertEquals(1, customerDao.list().size());
    }

    @Test
    public void createCustomerWithNullAttributes() throws Exception {
        assertEquals(0, customerDao.list().size());
        Customer customer = new Customer(101, null, null, null);
        Customer customerCreate = customerDao.create(customer);
        assertNull(customerCreate);
        assertEquals(0, customerDao.list().size());
    }

    @Test
    public void createCustomerWithNullPhone() throws Exception {
        assertEquals(0, customerDao.list().size());
        Customer customer = new Customer(101, "Lily", "Address", null);
        Customer customerCreate = customerDao.create(customer);
        assertNull(customerCreate);
        assertEquals(0, customerDao.list().size());
    }

    @Test
    public void createCustomerWithNullAddressAndPhone() throws Exception {
        assertEquals(0, customerDao.list().size());
        Customer customer = new Customer(101, "Lily", null, null);
        Customer customerCreate = customerDao.create(customer);
        assertNull(customerCreate);
        assertEquals(0, customerDao.list().size());
    }

    @Test
    public void createExistCustomer() throws Exception {
        assertEquals(0, customerDao.list().size());
        customerDao.create(customer1);
        Customer customerCreate = customerDao.create(customer1);
        assertNull(customerCreate);
        assertEquals(1, customerDao.list().size());
    }

    @Test
    public void getCustomerSuccessful() throws Exception {
        customerDao.create(customer1);
        Customer customerCreated = customerDao.create(customer2);
        Customer customerGet = customerDao.get((long) 2);
        assertEquals(customerCreated, customerGet);
    }

    @Test
    public void getNullCustomer() throws Exception {
        Customer customer = customerDao.get((long) 2);
        assertNull(customer);
    }

    @Test
    public void deleteCustomerSuccessful() throws Exception {
        customerDao.create(customer1);
        customerDao.create(customer2);
        customerDao.create(customer3);
        assertEquals(3, customerDao.list().size());
        long id = customerDao.delete((long) 2);
        assertEquals(2, id);
        assertEquals(2, customerDao.list().size());
    }

    @Test
    public void deleteNotExistCustomer() throws Exception {
        Long id = customerDao.delete((long) 2);
        assertNull(id);
    }

    @Test
    public void updateSuccessful() throws Exception {
        customerDao.create(customer1);
        customerDao.create(customer2);
        customerDao.update((long) 2, customer3);
        Customer customer = customerDao.get((long) 2);
        assertEquals(customer3.getName(), customer.getName());
        assertEquals(customer3.getAddress(), customer.getAddress());
        assertEquals(customer3.getTelephone(), customer.getTelephone());
    }

    @Test
    public void updateCustomerWithNullTelephone() throws Exception {
        customerDao.create(customer2);
        customer2.setTelephone(null);
        Customer customer = customerDao.update((long) 2, customer2);
        assertNull(customer);
    }

    @Test
    public void updateCustomerWithNullAddress() throws Exception {
        customerDao.create(customer2);
        customer2.setAddress(null);
        Customer customer = customerDao.update((long) 2, customer2);
        assertNull(customer);
    }

    @Test
    public void updateCustomerWithNullName() throws Exception {
        customerDao.create(customer2);
        customer2.setName(null);
        Customer customer = customerDao.update((long) 2, customer2);
        assertNull(customer);
    }

    @Test
    public void updateNotExistCustomer() throws Exception {
        Customer customer = customerDao.update((long) 1, customer2);
        assertNull(customer);
    }
}