package com.example.demo.model.internal.request;

/**
 * @author eguzman (2018.08.01 1:03 PM)
 */
public class SearchCustomerRequest {
    public enum SearchType {
           FIRST_AND_LAST_NAMES,
           PHONE_NUMBER
       }

       private SearchType searchType;
       private String firstName;
       private String lastName;
       private String phoneNumber;

    public SearchType getSearchType() {
        return searchType;
    }

    public SearchCustomerRequest setSearchType(SearchType searchType) {
        this.searchType = searchType;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SearchCustomerRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SearchCustomerRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SearchCustomerRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
