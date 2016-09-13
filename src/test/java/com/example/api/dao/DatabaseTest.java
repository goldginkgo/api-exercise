package com.example.api.dao;

import com.example.api.model.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {
    @Test
    public void getCustomers() throws Exception {
        Database database = new Database();
        assertEquals(new ArrayList<>(), database.getCustomers());
    }

    @Test
    public void setCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        Database database = new Database();
        database.setCustomers(customers);
        assertEquals(customers, database.getCustomers());
    }

}