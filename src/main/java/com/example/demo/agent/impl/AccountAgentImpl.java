package com.example.demo.agent.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.agent.AccountAgent;
import com.example.demo.dao.AccountDao;
import com.example.demo.model.internal.Account;
import com.example.demo.model.internal.Customer;
import com.example.demo.model.internal.request.CreateAccountRequest;
import com.example.demo.model.internal.request.GetAccountRequest;
import com.example.demo.model.internal.response.CreateAccountResponse;
import com.example.demo.model.internal.response.CreateCustomerResponse;
import com.example.demo.model.internal.response.GetAccountResponse;
import com.example.demo.model.internal.response.GetCustomerResponse;
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
                responseAccount = new GetAccountResponse.Account()
                    .setAccount_name(account.getFirstName())
                    .setAccount_id(account.getId())
                    .setAccount_Amount(account.getBalance());
            } else {
                responseAccount = null;
            }
        } else {
            responseAccount = null;
        }
        response.setAccount(responseAccount);
        return response;
    }
}
