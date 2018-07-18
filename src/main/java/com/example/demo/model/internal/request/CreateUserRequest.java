package com.example.demo.model.internal.request;

import com.example.demo.model.internal.User;

/**
 * @author eguzman (2018.07.02 2:27 PM)
 */
public class CreateUserRequest {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public CreateUserRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateUserRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
