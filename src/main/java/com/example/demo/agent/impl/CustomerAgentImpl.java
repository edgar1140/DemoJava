package com.example.demo.agent.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.agent.CustomerAgent;
import com.example.demo.dao.CustomerDao;
import com.example.demo.model.internal.Customer;
import com.example.demo.model.internal.request.CreateCustomerRequest;
import com.example.demo.model.internal.request.GetCustomerRequest;
import com.example.demo.model.internal.response.CreateCustomerResponse;
import com.example.demo.model.internal.response.GetCustomerResponse;
import com.example.demo.utility.StringUtils;

/**
 * @author eguzman (2018.07.09 10:06 AM)
 */
@Service
public class CustomerAgentImpl implements CustomerAgent {
    @Resource
    private CustomerDao customerDao;

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        final CreateCustomerResponse response = new CreateCustomerResponse();
                final String firstName = request.getFirstName();
                final String lastName = request.getLastName();

                if (StringUtils.isNotEmpty(firstName)
                    && StringUtils.isNotEmpty(lastName)) {
                    final Long id = customerDao.getCustomerIdNextValue();
                    final boolean success = customerDao.createCustomer(firstName, lastName, id);
                    final Customer customer = success ? customerDao.getCustomerById(id) : null ;
                    final CreateCustomerResponse.Customer responseCustomer;
                    if (customer != null) {
                        responseCustomer = new CreateCustomerResponse.Customer()
                            .setFirstName(customer.getFirstName())
                            .setLastName(customer.getLastName())
                            .setId(customer.getId());
                    } else {
                        responseCustomer = null;
                    }
                    response.setCustomer(responseCustomer);

                }
                return response;
    }

    @Override
    public GetCustomerResponse getCustomer(GetCustomerRequest request) {
        final GetCustomerResponse response = new GetCustomerResponse();
        final Long id = request.getId();
        final GetCustomerResponse.Customer responseCustomer;

        if (id != null) {
            final Customer customer = customerDao.getCustomerById(id);

            if (customer != null) {
                responseCustomer = new GetCustomerResponse.Customer()
                    .setFirstName(customer.getFirstName())
                    .setLastName(customer.getLastName())
                    .setId(customer.getId());
            } else {
                responseCustomer = null;
            }
        } else {
            responseCustomer = null;
        }
        response.setCustomer(responseCustomer);
        return response;
    }
}


