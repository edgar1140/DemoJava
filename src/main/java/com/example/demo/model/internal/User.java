package com.example.demo.model.internal;

import java.util.Date;

import com.example.demo.model.internal.response.GetUserResponse;

/**
 * @author eguzman (2018.07.02 2:10 PM)
 */
public class User {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String jobTitle;
    private Long locationId;
    private Long createUserId;
    private Date createDate;
    private Long expireUserId;
    private Date expirationDate;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public User setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public User setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public User setLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public User setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public User setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Long getExpireUserId() {
        return expireUserId;
    }

    public User setExpireUserId(Long expireUserId) {
        this.expireUserId = expireUserId;
        return this;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public User setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
}
