package com.blacksw.tags;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("repository")
public class JUnit5CustomersRepository {
    private String CUSTOMER_NAME = "John Smith";
    private CustomerRepository repository = new CustomerRepository();

    @Test
    public void testNonExistence() {
        boolean exists = repository.contains("John Smith");
        assertFalse(exists);
    }

    @Test
    public void testCustomerPersistence() {
        repository.persist(new Customer(CUSTOMER_NAME));
        assertTrue(repository.contains("John Smith"));
    }

}
