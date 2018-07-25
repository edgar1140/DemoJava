package com.example.demo.model.internal.request;

import com.example.demo.model.internal.response.CreateSubscriptionResponse;

/**
 * @author eguzman (2018.07.20 1:40 PM)
 */
public class CreateSubscriptionRequest {
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String serialNumber;
    private Boolean active;
    private Long accountId;
    private Long userId;
    private String planName;

    public String getFirstName() {
        return firstName;
    }

    public CreateSubscriptionRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateSubscriptionRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public CreateSubscriptionRequest setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public CreateSubscriptionRequest setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public CreateSubscriptionRequest setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public CreateSubscriptionRequest setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public CreateSubscriptionRequest setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getPlanName() {
        return planName;
    }

    public CreateSubscriptionRequest setPlanName(String planName) {
        this.planName = planName;
        return this;
    }
}
