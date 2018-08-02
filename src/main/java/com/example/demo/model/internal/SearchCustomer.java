package com.example.demo.model.internal;

/**
 * @author eguzman (2018.08.01 10:20 PM)
 */
public class SearchCustomer {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public SearchCustomer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SearchCustomer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SearchCustomer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SearchCustomer setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
