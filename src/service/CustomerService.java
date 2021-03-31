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
        for (Customer customer : customers) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }
}
