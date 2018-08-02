package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.internal.SearchCustomer;

/**
 * @author eguzman (2018.08.01 10:29 PM)
 */
public interface SearchCustomerDao {
    SearchCustomer createSearchCustomer(Long id, String firstName, String lastName, String phoneNumber);

    List<SearchCustomer> getCustomerByFirstAndLastName(String firstName, String lastName);

    List<SearchCustomer> createSearchingCustomer(Long id, String firstName, String lastName, String phoneNumber);

    List<SearchCustomer> getCustomerByPhoneNumber(String phoneNumber);
}
