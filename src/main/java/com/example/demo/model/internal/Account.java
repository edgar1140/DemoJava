package com.example.demo.model.internal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author eguzman (2018.07.12 9:01 PM)
 */
public class Account {
    private Long id;
    private Long customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private BigDecimal balance;
    private Long createUserId;
    private Date createDate;
    private Long expireUserId;
    private Date expirationDate;
    private List<Subscription> subscriptionList;

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Account setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Account setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Account setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Account setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Account setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public Account setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Account setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Long getExpireUserId() {
        return expireUserId;
    }

    public Account setExpireUserId(Long expireUserId) {
        this.expireUserId = expireUserId;
        return this;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Account setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public Account setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
        return this;
    }
}
