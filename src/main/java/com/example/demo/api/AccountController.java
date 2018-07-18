package com.example.demo.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.agent.AccountAgent;
import com.example.demo.model.internal.request.CreateAccountRequest;
import com.example.demo.model.internal.request.GetAccountRequest;
import com.example.demo.model.internal.response.CreateAccountResponse;
import com.example.demo.model.internal.response.GetAccountResponse;
import com.example.demo.model.internal.response.GetCustomerResponse;

import sun.management.Agent;

/**
 * @author eguzman (2018.07.16 11:20 AM)
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @Resource
    private AccountAgent accountAgent;
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public CreateAccountResponse create (@RequestBody CreateAccountRequest request) {
        return accountAgent.createAccount(request);
    }
    @RequestMapping (value = "/get-by-id", method = RequestMethod.POST)
    public GetAccountResponse getAccountId (@RequestBody GetAccountRequest resquest) {
        return accountAgent.getAccount(resquest);
    }
}
