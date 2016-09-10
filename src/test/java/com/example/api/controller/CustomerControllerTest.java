package com.example.api.controller;

import com.example.api.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    @Before
    public void setUp() throws Exception {
        customer1 = new Customer(101, "Lily", "12 Queen Street", "021-123-4567");
        customer2 = new Customer(102, "Jack", "11 Lake Road", "021-234-5678");
        customer3 = new Customer(103, "Jerry", "12 Lake Road", "027-345-6789");
    }

    @After
    public void tearDown() throws Exception {
        List<Map<String, Object>> customersMap = this.restTemplate.getForObject("/customers", ArrayList.class);
        for (Map<String, Object> map : customersMap) {
            restTemplate.delete("/customers/" + map.get("id"));
        }
    }

    @Test
    public void createCustomer() throws Exception {
        ResponseEntity entity = this.restTemplate.postForEntity("/customers", customer1, Customer.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getNotExistCustomer() throws Exception {
        String message = this.restTemplate.getForObject("/customers/1", String.class);
        assertEquals("No Customer found for ID 1", message);
    }

    @Test
    public void getExistCustomer() throws Exception {
        // add 1 customer
        ResponseEntity entity = this.restTemplate.postForEntity("/customers", customer1, Customer.class);
        Customer customer = (Customer)entity.getBody();
        // get customer
        ResponseEntity entityGet = this.restTemplate.getForEntity("/customers/" + customer.getId(), Customer.class);
        assertThat(entityGet.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getCustomers() throws Exception {
        // no customers
        List<Map<String, Object>> customersMap = this.restTemplate.getForObject("/customers", ArrayList.class);
        assertEquals(0, customersMap.size());
        // add 2 customers
        this.restTemplate.postForEntity("/customers", customer1, Customer.class);
        this.restTemplate.postForEntity("/customers", customer2, Customer.class);
        // get customers
        List<Map<String, Object>> customersMap2 = this.restTemplate.getForObject("/customers", ArrayList.class);
        assertEquals(2, customersMap2.size());
    }

    @Test
    public void deleteCustomer() throws Exception {
        // add 1 customer
        ResponseEntity entity = this.restTemplate.postForEntity("/customers", customer1, Customer.class);
        Customer customer = (Customer)entity.getBody();
        // delete customer
        restTemplate.delete("/customers/" + customer.getId());
        // get customers
        List<Map<String, Object>> customersMap = this.restTemplate.getForObject("/customers", ArrayList.class);
        assertEquals(0, customersMap.size());
    }

    @Test
    public void updateCustomer() throws Exception {
        // add 1 customer
        ResponseEntity entity = this.restTemplate.postForEntity("/customers", customer1, Customer.class);
        Customer customer = (Customer)entity.getBody();
        long customerId = customer.getId();
        // update customer
        Customer customerNew = new Customer(customerId, "Lily", "12 High Street", "021-123-4567");
        restTemplate.put("/customers/" + customerId, customerNew);
        //get the customer
        Customer customerGet = this.restTemplate.getForObject("/customers/" + customerId, Customer.class);
        assertEquals("12 High Street", customerGet.getAddress());
    }

}