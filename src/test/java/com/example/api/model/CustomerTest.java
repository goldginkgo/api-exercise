package com.example.api.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CustomerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void equalsTest() throws Exception {
        Customer customer1 = new Customer(101, "Lily", "12 Queen Street", "021-123-4567");
        Customer customer2;
        // two equal customers
        customer2 = new Customer(102, "Lily", "12 Queen Street", "021-123-4567");
        assertTrue(customer1.equals(customer2));
        // customer compared is null
        assertFalse(customer1.equals(null));
        // customer compared is not a Customer object
        assertFalse(customer1.equals(""));
        // name not equal
        customer2 = new Customer(102, "Kelven", "12 Queen Street", "021-123-4567");
        assertFalse(customer1.equals(customer2));
        // address not equal
        customer2 = new Customer(102, "Lily", "11 Queen Street", "021-123-4567");
        assertFalse(customer1.equals(customer2));
        // telephone not equal
        customer2 = new Customer(102, "Lily", "12 Queen Street", "121-123-4567");
        assertFalse(customer1.equals(customer2));
    }

    @Test
    public void hashCodeTest() throws Exception {
        Customer customer1 = new Customer(101, "Lily", "12 Queen Street", "021-123-4567");
        Customer customer2 = new Customer(102, "Lily", "12 Queen Street", "021-123-4567");
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }


}