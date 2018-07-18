package com.example.demo.model.internal.response;

/**
 * @author eguzman (2018.07.09 2:38 PM)
 */
public class GetCustomerResponse {
    public static class Customer {
        private String firstName;
        private String lastName;
        private Long id;
        private Long locationId;
        private String jobTitle;

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

        public Long getLocationId() {
            return locationId;
        }

        public Customer setLocationId(Long locationId) {
            this.locationId = locationId;
            return this;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public Customer setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
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
