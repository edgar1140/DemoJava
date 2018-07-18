package com.example.demo.model.internal.request;

import com.example.demo.model.internal.Customer;

/**
 * @author eguzman (2018.07.09 2:42 PM)
 */
public class CreateCustomerRequest {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public CreateCustomerRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateCustomerRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
