package com.example.demo.agent.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.agent.AccountAgent;
import com.example.demo.dao.AccountDao;
import com.example.demo.model.internal.Account;
import com.example.demo.model.internal.Subscription;
import com.example.demo.model.internal.request.CreateAccountRequest;
import com.example.demo.model.internal.request.GetAccountRequest;
import com.example.demo.model.internal.response.CreateAccountResponse;
import com.example.demo.model.internal.response.GetAccountResponse;
import com.example.demo.utility.StringUtils;

/**
 * @author eguzman (2018.07.16 1:23 PM)
 */
@Service
public class AccountAgentImpl implements AccountAgent {
    @Resource
    private AccountDao accountDao;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        final CreateAccountResponse response = new CreateAccountResponse();
        final String firstName = request.getFirstName();
        final String middleName = request.getMiddleName();
        final String lastName = request.getLastName();
        final Long customerId = request.getCustomerId();
        final Long userId = request.getUserId();

        if (StringUtils.isNotEmpty(firstName) && StringUtils.isNotEmpty(lastName) && customerId != null &&
            userId != null) {
            final Long accountId = accountDao.getAccountIdNextValue();
            final boolean success = accountDao.createAccount(firstName, middleName, lastName, accountId, customerId, userId);
            final Account account = success ? accountDao.getAccountById(accountId) : null;
            final CreateAccountResponse.Account responseAccount;
            if (account != null) {
                responseAccount = new CreateAccountResponse.Account()
                    .setFirstName(account.getFirstName())
                    .setMiddleName(account.getMiddleName())
                    .setLastName(account.getLastName())
                    .setCustomerId(account.getCustomerId())
                    .setAccountId(account.getId());

            } else {
                responseAccount = null;
            }
            response.setAccount(responseAccount);
        }
        return response;

    }

    @Override
    public GetAccountResponse getAccount(GetAccountRequest request) {
        final GetAccountResponse response = new GetAccountResponse();
        final Long id = request.getAccountId();
        final GetAccountResponse.Account responseAccount;

        if (id != null) {
            final Account account = accountDao.getAccountById(id);

            if (account != null) {
                final List<Subscription> subscriptionFromDataBaseList = account.getSubscriptionList();
                final List<GetAccountResponse.Subscription> subscriptionList = toSubscriptionList(subscriptionFromDataBaseList);
                responseAccount = new GetAccountResponse.Account()
                    .setAccountName(account.getFirstName())
                    .setAccountId(account.getId())
                    .setAccountAmount(account.getBalance())
                    .setSubscriptions(subscriptionList);
            } else {
                responseAccount = null;
            }
        } else {
            responseAccount = null;
        }
        response.setAccount(responseAccount);
        return response;
    }

    private List<GetAccountResponse.Subscription> toSubscriptionList(List<Subscription> subscriptions) {
        List<GetAccountResponse.Subscription> subscriptionList;
        if (subscriptions != null && !subscriptions.isEmpty()) {
            subscriptionList = new ArrayList<>();
            for (Subscription subscriptionFromDataBase : subscriptions) {
                final GetAccountResponse.Subscription subscription = new GetAccountResponse.Subscription()
                    .setFirstName(subscriptionFromDataBase.getFirstName())
                    .setLastName(subscriptionFromDataBase.getLastName())
                    .setId(subscriptionFromDataBase.getId())
                    .setSerialNumber(subscriptionFromDataBase.getSerialNumber())
                    .setPlanName(subscriptionFromDataBase.getPlanName())
                    .setPhoneNumber(subscriptionFromDataBase.getPhoneNumber());
                subscriptionList.add(subscription);

            }

        } else {
            subscriptionList = null;
        }
        return subscriptionList;
    }
}
