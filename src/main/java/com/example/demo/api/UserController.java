package com.example.demo.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.agent.UserAgent;
import com.example.demo.model.internal.User;
import com.example.demo.model.internal.request.CreateUserRequest;
import com.example.demo.model.internal.request.GetUserRequest;
import com.example.demo.model.internal.response.CreateUserResponse;
import com.example.demo.model.internal.response.GetUserResponse;

/**
 * @author eguzman (2018.07.02 1:54 PM)
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserAgent userAgent;
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public CreateUserResponse create (@RequestBody CreateUserRequest request) {
        return userAgent.createUser(request);
    }
    @RequestMapping(value ="/get-by-id",method = RequestMethod.POST)
    public GetUserResponse getUserById (@RequestBody GetUserRequest request) {
        return userAgent.getUser(request);
    }
}
