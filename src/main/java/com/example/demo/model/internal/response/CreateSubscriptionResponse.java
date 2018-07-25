package com.example.demo.model.internal.response;

/**
 * @author eguzman (2018.07.20 2:54 PM)
 */
public class CreateSubscriptionResponse {
    public static class Subscription {
        private String firstName;
        private String lastName;
        private Long phoneNumber;
        private String serialNumber;
        private Boolean active;
        private Long userId;
        private Long subscriptionId;
        private String planName;
        private Long accountId;

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

        public Long getUserId() {
            return userId;
        }

        public Subscription setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Long getSubscriptionId() {
            return subscriptionId;
        }

        public Subscription setSubscriptionId(Long subscriptionId) {
            this.subscriptionId = subscriptionId;
            return this;
        }

        public String getPlanName() {
            return planName;
        }

        public Subscription setPlanName(String planName) {
            this.planName = planName;
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
    public Subscription subscription;

    public Subscription getSubscription () {
        return subscription;
    }
    public CreateSubscriptionResponse setSubscription(Subscription subscription) {
        this.subscription = subscription;
        return this;
    }
}
