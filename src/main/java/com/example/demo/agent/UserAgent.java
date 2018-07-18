package com.example.demo.agent;

import com.example.demo.model.internal.request.CreateUserRequest;
import com.example.demo.model.internal.request.GetUserRequest;
import com.example.demo.model.internal.response.CreateUserResponse;
import com.example.demo.model.internal.response.GetUserResponse;

/**
 * @author eguzman (2018.07.03 3:49 PM)
 */
public interface UserAgent {
    CreateUserResponse createUser(CreateUserRequest request);

    GetUserResponse getUser(GetUserRequest request);

}

