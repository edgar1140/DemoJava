package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.internal.SearchCustomer;

/**
 * @author eguzman (2018.08.01 10:29 PM)
 */
public interface SearchCustomerDao {

    List<SearchCustomer> getCustomerByFirstAndLastName(String firstName, String lastName);

    List<SearchCustomer> getCustomerByPhoneNumber(String phoneNumber);
}
