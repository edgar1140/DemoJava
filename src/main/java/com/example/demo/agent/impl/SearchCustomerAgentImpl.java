package com.example.demo.agent.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.agent.SearchCustomerAgent;
import com.example.demo.dao.SearchCustomerDao;
import com.example.demo.model.internal.SearchCustomer;
import com.example.demo.model.internal.request.SearchCustomerRequest;
import com.example.demo.model.internal.response.SearchCustomerResponse;

/**
 * @author eguzman (2018.08.02 8:36 AM)
 */
@Service
public class SearchCustomerAgentImpl implements SearchCustomerAgent {
    @Resource
    private SearchCustomerDao searchCustomerDao;

    @Override
    public SearchCustomerResponse searchCustomer(SearchCustomerRequest request) {
        final SearchCustomerResponse response = new SearchCustomerResponse();
        final SearchCustomerRequest.SearchType searchType = request.getSearchType();
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();
        final String phoneNumber = request.getPhoneNumber();

        final List<SearchCustomer> searchCustomerList;
        if (SearchCustomerRequest.SearchType.FIRST_AND_LAST_NAMES.equals(searchType)) {
            searchCustomerList = searchCustomerDao.getCustomerByFirstAndLastName(firstName, lastName);

        } else if (SearchCustomerRequest.SearchType.PHONE_NUMBER.equals(searchType)) {
            searchCustomerList = searchCustomerDao.getCustomerByPhoneNumber(phoneNumber);

        } else {
            searchCustomerList = null;
        }


        final List<SearchCustomerResponse.Customer> searchCustomers;
        if (searchCustomerList != null) {
            searchCustomers = new ArrayList<>();
            for (SearchCustomer customer : searchCustomerList) {
                SearchCustomerResponse.Customer responsecustomer = new SearchCustomerResponse.Customer()
                    .setId(customer.getId())
                    .setFirstName(customer.getFirstName())
                    .setLastName(customer.getLastName())
                    .setPhoneName(customer.getPhoneNumber());
                searchCustomers.add(responsecustomer);
            }
        } else {
            searchCustomers = null;
        }
        response.setCustomers(searchCustomers);
        return response;
    }
}
