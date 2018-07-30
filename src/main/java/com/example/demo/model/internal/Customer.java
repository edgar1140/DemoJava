package com.example.demo.model.internal;

import java.util.Date;
import java.util.List;

/**
 * @author eguzman (2018.07.09 11:21 AM)
 */
public class Customer {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long createUserId;
    private Date createDate;
    private Long expireUserId;
    private Date expirationDate;
    private List<Account> accountList;

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Customer setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public Customer setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Customer setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Long getExpireUserId() {
        return expireUserId;
    }

    public Customer setExpireUserId(Long expireUserId) {
        this.expireUserId = expireUserId;
        return this;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Customer setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public Customer setAccountList(List<Account> accountList) {
        this.accountList = accountList;
        return this;
    }

}
