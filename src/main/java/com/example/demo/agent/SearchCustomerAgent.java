package com.example.demo.agent;

import com.example.demo.model.internal.request.SearchCustomerRequest;
import com.example.demo.model.internal.response.SearchCustomerResponse;

/**
 * @author eguzman (2018.08.02 8:39 AM)
 */
public interface SearchCustomerAgent {
    SearchCustomerResponse searchCustomer (SearchCustomerRequest request);
}
