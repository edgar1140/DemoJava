package com.example.demo.model.internal.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author eguzman (2018.07.09 2:38 PM)
 */
public class GetCustomerResponse {
    public static class Customer {
        private String firstName;
        private String lastName;
        private Long id;
        private List<Account> accounts;

        public String getFirstName() {
            return firstName;
        }

        public Customer setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public Customer setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Long getId() {
            return id;
        }

        public Customer setId(Long id) {
            this.id = id;
            return this;
        }

        public List<Account> getAccounts() {
            return accounts;
        }

        public Customer setAccounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }
    }

    public static class Account {
        private String firstName;
        private String lastName;
        private Long id;
        private BigDecimal balance;
        private List<Subscription> subscriptionList;

        public String getFirstName() {
            return firstName;
        }

        public Account setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public Account setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Long getId() {
            return id;
        }

        public Account setId(Long id) {
            this.id = id;
            return this;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public Account setBalance(BigDecimal balance) {
            this.balance = balance;
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

    public static class Subscription {
        private String firstName;
        private String lastName;
        private Long phoneNumber;
        private String serialNumber;
        private Long subscriptionId;

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

        public Long getSubscriptionId() {
            return subscriptionId;
        }

        public Subscription setSubscriptionId(Long subscriptionId) {
            this.subscriptionId = subscriptionId;
            return this;
        }
    }

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public GetCustomerResponse setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }
}
