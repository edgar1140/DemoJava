package com.example.demo.model.internal.request;

import com.example.demo.model.internal.Account;
import com.example.demo.model.internal.response.GetAccountResponse;

/**
 * @author eguzman (2018.07.13 9:09 AM)
 */
public class GetAccountRequest {
    private Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public GetAccountRequest setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
}

