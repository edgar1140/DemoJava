package com.example.demo.dao;

import com.example.demo.model.internal.Customer;

/**
 * @author eguzman (2018.07.09 11:11 AM)
 */
public interface CustomerDao {
    boolean createCustomer(String firstName, String lastName, Long id, Long UserId);

    Long  getCustomerIdNextValue();

    Customer getCustomerById(Long id);
}
