package com.example.demo.model.internal.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.example.demo.model.internal.Subscription;

/**
 * @author eguzman (2018.07.12 9:26 PM)
 */
public class GetAccountResponse {
    public static class Account {
        private String accountName;
        private Long accountId;
        private BigDecimal accountAmount;
        private List<Subscription> subscriptions;

        public String getAccountName() {
            return accountName;
        }

        public Account setAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Long getAccountId() {
            return accountId;
        }

        public Account setAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public BigDecimal getAccountAmount() {
            return accountAmount;
        }

        public Account setAccountAmount(BigDecimal accountAmount) {
            this.accountAmount = accountAmount;
            return this;
        }

        public List<Subscription> getSubscriptions() {
            return subscriptions;
        }

        public Account setSubscriptions(List<Subscription> subscriptions) {
            this.subscriptions = subscriptions;
            return this;
        }
    }

    public static class Subscription {
        private Long id;
        private String firstName;
        private String lastName;
        private Long phoneNumber;
        private String serialNumber;
        private Boolean active;
        private String planName;

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
    }

    public Account account;

    public Account getAccount() {
        return account;
    }

    public GetAccountResponse setAccount(Account account) {
        this.account = account;
        return this;
    }
}
