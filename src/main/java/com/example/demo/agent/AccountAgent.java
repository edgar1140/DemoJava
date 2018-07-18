package com.example.demo.agent;

import com.example.demo.model.internal.request.CreateAccountRequest;
import com.example.demo.model.internal.request.GetAccountRequest;
import com.example.demo.model.internal.response.CreateAccountResponse;
import com.example.demo.model.internal.response.GetAccountResponse;

/**
 * @author eguzman (2018.07.16 1:24 PM)
 */
public interface AccountAgent {
    CreateAccountResponse createAccount(CreateAccountRequest request);

    GetAccountResponse getAccount(GetAccountRequest request);
}
