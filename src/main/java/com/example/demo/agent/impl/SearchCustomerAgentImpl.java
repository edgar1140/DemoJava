package com.example.demo.agent.impl;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    public SearchCustomerResponse searchCustomer(SearchCustomerRequest request) {
        final SearchCustomerResponse response = new SearchCustomerResponse();
        final SearchCustomerRequest.SearchType searchType = request.getSearchType();
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();
        final String phoneNumber = request.getPhoneNumber();

        final List<SearchCustomer> SearchCustomerList;
        if (SearchCustomerRequest.SearchType.FIRST_AND_LAST_NAMES.equals(searchType)) {
            SearchCustomerList = SearchCustomerDao.getCustomerByFirstAndLastName(firstName, lastName, phoneNumber);

        } else if (SearchCustomerRequest.SearchType.PHONE_NUMBER.equals(searchType)) {
            SearchCustomerList = SearchCustomerDao.getCustomerByPhoneNumber(phoneNumber);

        } else {
            SearchCustomerList = null;
        }

        final List<SearchCustomerResponse.SearchCustomer> searchCustomer;
        if (SearchCustomerList != null) {
            searchCustomer() = new ArrayList<>();
            for (SearchCustomerList:
                 SearchCustomerList) {
                SearchCustomerResponse.SearchCustomer responsecustomer = new SearchCustomerResponse.SearchCustomer()
                    .setId(searchCustomer.getId())
                    .setFirstName(searchCustomer.getFirstName())
                    .setLastName(searchCustomer.getLastName());
                searchCustomer.add(responsecustomer);
            }
        } else {
            searchCustomer = null;
        }
        response.setCustomers(searchCustomer);
        return response;
    }
}
