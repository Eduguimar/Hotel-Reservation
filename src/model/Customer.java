package model;

import java.util.regex.Pattern;

public class Customer {
    String firstName;
    String lastName;
    String email;
    String emailRegex = "^(.+)@(.+).(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = validateEmail(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validateEmail(email);
    }

    private String validateEmail(String email) {
        if (pattern.matcher(email).matches()) {
            return email;
        }
        throw new IllegalArgumentException("The email format is not valid!");
    }

    public String toString() {
        return "Customer: " + firstName + " " + lastName + "\nEmail: " + email;
    }
}
