package com.example.demo.agent.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.agent.UserAgent;
import com.example.demo.dao.UserDao;
import com.example.demo.model.internal.User;
import com.example.demo.model.internal.request.CreateUserRequest;
import com.example.demo.model.internal.request.GetUserRequest;
import com.example.demo.model.internal.response.CreateUserResponse;
import com.example.demo.model.internal.response.GetUserResponse;
import com.example.demo.utility.StringUtils;

import io.micrometer.core.instrument.Meter;

/**
 * @author eguzman (2018.07.03 3:58 PM)
 */
@Service
public class UserAgentImpl implements UserAgent {
    @Resource
    private UserDao userDao;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        final CreateUserResponse response = new CreateUserResponse();
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();

        if (StringUtils.isNotEmpty(firstName)
            && StringUtils.isNotEmpty(lastName)) {
            final Long id = userDao.getUserIdNextValue();
            final boolean success = userDao.createUser(firstName, lastName, id);
            final User user = success ? userDao.getUserById(id) : null;
            final CreateUserResponse.User responseUser;

            if (user != null) {
                responseUser = new CreateUserResponse.User()
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setId(user.getId());
            } else {
                responseUser = null;
            }
            response.setUser(responseUser);

        }

        return response;
    }

    @Override
    public GetUserResponse getUser(GetUserRequest request) {
        final GetUserResponse response = new GetUserResponse();
        final Long id = request.getId();
        final GetUserResponse.User responseUser;

        if (id != null) {
            final User user = userDao.getUserById(id);

            if (user != null) {
                responseUser = new GetUserResponse.User()
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setId(user.getId())
                    .setLocationId(user.getLocationId())
                    .setJobTitle(user.getJobTitle());

            } else {
                responseUser = null;
            }

        } else {
            responseUser = null;
        }
        response.setUser(responseUser);
        return response;

    }
}