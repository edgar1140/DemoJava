package com.example.demo.model.internal.response;

import com.example.demo.model.internal.request.CreateCustomerRequest;

/**
 * @author eguzman (2018.07.09 2:38 PM)
 */
public class CreateCustomerResponse {

    public static class Customer {
        private String firstName;
        private String lastName;
        private Long id;

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
    }

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public CreateCustomerResponse setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

}
