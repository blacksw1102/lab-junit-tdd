package org.example.tags;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    public boolean contains(String name) {
        return this.customers.stream().anyMatch(customer -> customer.equals(name));
    }

    public void persist(Customer customer) {
        this.customers.add(customer);
    }
}
