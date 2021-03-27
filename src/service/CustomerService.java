package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService customerService = null;
    private Collection<Customer> customers;

    private CustomerService() {
        this.customers = new ArrayList<>();
    }

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        customers.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail) {
        Optional<Customer> customer = customers.stream().filter(c -> customerEmail.equals(c.getEmail())).findFirst();
        return customer.orElse(null);
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }
}
