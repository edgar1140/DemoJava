package com.example.demo.agent.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.agent.CustomerAgent;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.SearchCustomerDao;
import com.example.demo.model.internal.Account;
import com.example.demo.model.internal.Customer;
import com.example.demo.model.internal.SearchCustomer;
import com.example.demo.model.internal.Subscription;
import com.example.demo.model.internal.request.CreateCustomerRequest;
import com.example.demo.model.internal.request.GetCustomerRequest;
import com.example.demo.model.internal.request.SearchCustomerRequest;
import com.example.demo.model.internal.response.CreateCustomerResponse;
import com.example.demo.model.internal.response.GetCustomerResponse;
import com.example.demo.model.internal.response.SearchCustomerResponse;
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
                        final List<Subscription> subscriptionFromDataBaseList = accountFromDataBase.getSubscriptionList();
                        final boolean extractSubscriptions = subscriptionFromDataBaseList != null && !subscriptionFromDataBaseList.isEmpty();
                        List<GetCustomerResponse.Subscription> subscriptionList = null;
                        if (extractSubscriptions) {
                            subscriptionList = new ArrayList<>();
                            for (Subscription subscriptionFromDataBase : subscriptionFromDataBaseList) {
                                final GetCustomerResponse.Subscription subscription = new GetCustomerResponse.Subscription()
                                    .setSubscriptionId(subscriptionFromDataBase.getId())
                                    .setFirstName(subscriptionFromDataBase.getFirstName())
                                    .setLastName(subscriptionFromDataBase.getLastName())
                                    .setPhoneNumber(subscriptionFromDataBase.getPhoneNumber())
                                    .setSerialNumber(subscriptionFromDataBase.getSerialNumber());
                                subscriptionList.add(subscription);
                            }
                        }
                        final GetCustomerResponse.Account account = new GetCustomerResponse.Account()
                            .setFirstName(accountFromDataBase.getFirstName())
                            .setLastName(accountFromDataBase.getLastName())
                            .setId(accountFromDataBase.getId())
                            .setBalance(accountFromDataBase.getBalance())
                            .setSubscriptionList(subscriptionList);
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

        } else {
            responseCustomer = null;
        }
        response.setCustomer(responseCustomer);
        return response;
    }

    @Override
    public SearchCustomerResponse searchCustomer(SearchCustomerRequest request) {
        final SearchCustomerResponse response = new SearchCustomerResponse();
        final SearchCustomerRequest.SearchType searchType = request.getSearchType();
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();
        final String phoneNumber = request.getPhoneNumber();

        final List<SearchCustomer> SearchCustomerList;
        if (SearchCustomerRequest.SearchType.FIRST_AND_LAST_NAMES.equals(searchType)) {
            SearchCustomerList = searchcustomerDao.getCustomerByFirstAndLastName(firstName, lastName, phoneNumber);

        } else if (SearchCustomerRequest.SearchType.PHONE_NUMBER.equals(searchType)) {
            SearchCustomerList = customerDao.getCustomerByPhoneNumber(phoneNumber);

        } else {
            SearchCustomerList = null;
        }

        final List<SearchCustomerResponse.SearchCustomer> customers;
        if (SearchCustomerList != null) {
            searchCustomer() = new ArrayList<>();
            for (SearchCustomerList  : SearchCustomerList) {
                SearchCustomerResponse.Customer responsecustomer = new SearchCustomerResponse.SearchCustomer()
                    .setId(customer.getId())
                    .setFirstName(customer.getFirstName())
                    .setLastName(customer.getLastName());
                customers.add(responsecustomer);
            }
        } else {
            customers = null;
        }
        response.setCustomers(customers);
        return response;
    }

}


