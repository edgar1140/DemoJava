package com.example.demo.model.internal;

import java.util.Date;

/**
 * @author eguzman (2018.07.20 10:23 AM)
 */
public class Subscription {
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String serialNumber;
    private Boolean active;
    private String planName;
    private Long createUserId;
    private Date createDate;
    private Long expireUserId;
    private Date expirationDate;
    private Long accountId;

    public Long getId() {
        return id;
    }

    public Subscription setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Subscription setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Subscription setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Subscription setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Subscription setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public Subscription setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public String getPlanName() {
        return planName;
    }

    public Subscription setPlanName(String planName) {
        this.planName = planName;
        return this;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public Subscription setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Subscription setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Long getExpireUserId() {
        return expireUserId;
    }

    public Subscription setExpireUserId(Long expireUserId) {
        this.expireUserId = expireUserId;
        return this;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Subscription setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Subscription setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
}
