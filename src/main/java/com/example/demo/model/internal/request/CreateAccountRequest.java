package com.example.demo.model.internal.request;

import java.math.BigDecimal;

import com.example.demo.model.internal.Account;
import com.example.demo.model.internal.response.GetAccountResponse;

/**
 * @author eguzman (2018.07.13 8:33 AM)
 */
public class CreateAccountRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private Long customerId;
    private Long userId;

    public String getFirstName() {
        return firstName;
    }

    public CreateAccountRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public CreateAccountRequest setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateAccountRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public CreateAccountRequest setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public CreateAccountRequest setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
