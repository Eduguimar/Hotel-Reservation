package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService customerService = null;
    private static Collection<Customer> customers;

    private CustomerService() {
        this.customers = new ArrayList<>();
    }

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public static void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        customers.add(new Customer(firstName, lastName, email));
    }

    public static Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public static Collection<Customer> getAllCustomers() {
        return customers;
    }
}
