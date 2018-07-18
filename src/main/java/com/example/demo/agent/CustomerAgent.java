package com.example.demo.agent;

import com.example.demo.model.internal.request.CreateCustomerRequest;
import com.example.demo.model.internal.request.GetCustomerRequest;
import com.example.demo.model.internal.response.CreateCustomerResponse;
import com.example.demo.model.internal.response.GetCustomerResponse;

/**
 * @author eguzman (2018.07.09 2:32 PM)
 */
public interface CustomerAgent {
    CreateCustomerResponse createCustomer(CreateCustomerRequest request);

    GetCustomerResponse getCustomer(GetCustomerRequest request);

}
