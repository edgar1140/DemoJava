package com.example.demo.model.internal.response;

import java.util.List;

/**
 * @author eguzman (2018.08.01 1:04 PM)
 */
public class SearchCustomerResponse {
    public static class Customer{
        private Long id;
        private String firstName;
        private String lastName;

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

        public String getLastName() {
            return lastName;
        }

        public Customer setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
    }

    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public SearchCustomerResponse setCustomers(List<Customer> customers) {
        this.customers = customers;
        return this;
    }
}
