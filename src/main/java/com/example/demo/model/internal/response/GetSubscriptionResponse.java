package com.example.demo.model.internal.response;

/**
 * @author eguzman (2018.07.20 3:06 PM)
 */
public class GetSubscriptionResponse {
    public static class Subscription {
        private String firstName;
        private String lastName;
        private String serialNumber;
        private Long subscriptionId;
        private String planName;
        private Long phoneNumber;

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

        public String getPlanName() {
            return planName;
        }

        public Subscription setPlanName(String planName) {
            this.planName = planName;
            return this;
        }

        public Long getPhoneNumber() {
            return phoneNumber;
        }

        public Subscription setPhoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
    }

    public Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    public GetSubscriptionResponse setSubscription(Subscription subscription) {
        this.subscription = subscription;
        return this;
    }
}
