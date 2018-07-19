package com.example.demo.agent.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.agent.CustomerAgent;
import com.example.demo.dao.CustomerDao;
import com.example.demo.model.internal.Account;
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
        final Long userId = request.getUserId();

        if (StringUtils.isNotEmpty(firstName)
            && StringUtils.isNotEmpty(lastName) && userId != null) {
            final Long id = customerDao.getCustomerIdNextValue();
            final boolean success = customerDao.createCustomer(firstName, lastName, id, userId);
            final Customer customer = success ? customerDao.getCustomerById(id) : null;
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
                final List<GetCustomerResponse.Account> accountList = new ArrayList<>();
                final List<Account> accountFromDataBaseList = customer.getAccountList();

                final boolean extractAccounts = accountFromDataBaseList != null && !accountFromDataBaseList.isEmpty();
                if (extractAccounts) {
                    for (Account accountFromDataBase : accountFromDataBaseList) {
                        final GetCustomerResponse.Account account = new GetCustomerResponse.Account()
                            .setFirstName(accountFromDataBase.getFirstName())
                            .setLastName(accountFromDataBase.getLastName())
                            .setId(accountFromDataBase.getId())
                            .setBalance(accountFromDataBase.getBalance());
                        accountList.add(account);
                    }
                }

                responseCustomer = new GetCustomerResponse.Customer()
                    .setFirstName(customer.getFirstName())
                    .setLastName(customer.getLastName())
                    .setId(customer.getId())
                    .setAccounts(accountList);

            } else {
                responseCustomer = null;
            }

        }  else {
            responseCustomer = null;
        }
        response.setCustomer(responseCustomer);
        return response;
    }
}


