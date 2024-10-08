package com.blacksw.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("repository")
public class CustomerRepositoryTest {
    private final String CUSTOMER_NAME = "John Smith";
    private final CustomerRepository repository = new CustomerRepository();

    @Test
    void testNonExistence() {
        boolean exists = repository.contains("John Smith");
        assertFalse(exists);
    }

    @Test
    void testCustomerPersistence() {
        repository.persist(new Customer(CUSTOMER_NAME));
        boolean exists = repository.contains("John Smith");
        assertTrue(exists);
    }

}
