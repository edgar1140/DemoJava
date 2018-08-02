package com.example.demo.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.agent.CustomerAgent;
import com.example.demo.model.internal.request.CreateCustomerRequest;
import com.example.demo.model.internal.request.CreateUserRequest;
import com.example.demo.model.internal.request.GetCustomerRequest;
import com.example.demo.model.internal.request.SearchCustomerRequest;
import com.example.demo.model.internal.response.CreateCustomerResponse;
import com.example.demo.model.internal.response.GetCustomerResponse;
import com.example.demo.model.internal.response.SearchCustomerResponse;

/**
 * @author eguzman (2018.07.09 3:11 PM)
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Resource
    private CustomerAgent customerAgent;
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public CreateCustomerResponse create (@RequestBody CreateCustomerRequest request){
        return customerAgent.createCustomer(request);
    }
    @RequestMapping(value = "get-by-id",method = RequestMethod.POST)
    public GetCustomerResponse getCustomerById (@RequestBody GetCustomerRequest request) {
        return customerAgent.getCustomer(request);
    }
    @RequestMapping (value = "Search-customer", method = RequestMethod.POST)
    public SearchCustomerResponse searchCustomers(@RequestBody SearchCustomerRequest request) {
        return customerAgent.searchCustomer(request);
    }

}
