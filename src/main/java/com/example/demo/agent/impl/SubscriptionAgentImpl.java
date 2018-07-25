package com.example.demo.agent.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.agent.SubscriptionAgent;
import com.example.demo.dao.SubscriptionDao;
import com.example.demo.model.internal.Subscription;
import com.example.demo.model.internal.request.CreateSubscriptionRequest;
import com.example.demo.model.internal.request.GetSubscriptionRequest;
import com.example.demo.model.internal.response.CreateSubscriptionResponse;
import com.example.demo.model.internal.response.GetSubscriptionResponse;
import com.example.demo.utility.StringUtils;

import sun.util.resources.cldr.agq.LocaleNames_agq;

/**
 * @author eguzman (2018.07.20 4:46 PM)
 */
@Service
public class SubscriptionAgentImpl implements SubscriptionAgent {
    @Resource
    private SubscriptionDao subscriptionDao;

    @Override
    public CreateSubscriptionResponse createSubscription(CreateSubscriptionRequest request) {
        final CreateSubscriptionResponse response = new CreateSubscriptionResponse();
        final Long accountId = request.getAccountId();
        final Long create_user_id = request.getUserId();
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();
        final String serialNumber = request.getSerialNumber();
        final Boolean active = request.getActive();
        final Long phoneNumber = request.getPhoneNumber();

        if (StringUtils.isNotEmpty(firstName)
            && StringUtils.isNotEmpty(lastName)) {
            final Long subscriptionId = subscriptionDao.getSubscriptionIdNextValue();
            final boolean success = subscriptionDao.createSubscription(accountId, subscriptionId, create_user_id, firstName, lastName, serialNumber, active, phoneNumber);
            final Subscription subscription = success ? subscriptionDao.getSubscriptionById(subscriptionId) : null;
            final CreateSubscriptionResponse.Subscription responseSubscription;

            if (subscription != null) {
                responseSubscription = new CreateSubscriptionResponse.Subscription()
                    .setAccountId(subscription.getAccountId())
                    .setSubscriptionId(subscription.getId())
                    .setUserId(subscription.getCreateUserId())
                    .setFirstName(subscription.getFirstName())
                    .setLastName(subscription.getLastName())
                    .setSerialNumber(subscription.getSerialNumber())
                    .setActive(subscription.getActive())
                    .setPhoneNumber(subscription.getPhoneNumber());
            } else {
                responseSubscription = null;
            }
            response.setSubscription(responseSubscription);

        }

        return response;
    }

    @Override
    public GetSubscriptionResponse getSubscription(GetSubscriptionRequest request) {
        final GetSubscriptionResponse response = new GetSubscriptionResponse();
        final Long id = request.getId();
        final GetSubscriptionResponse.Subscription responseSubscription;

        if (id != null) {
            final Subscription subscription = subscriptionDao.getSubscriptionById(id);

            if (subscription != null) {
                responseSubscription = new GetSubscriptionResponse.Subscription()
                    .setFirstName(subscription.getFirstName())
                    .setLastName(subscription.getLastName())
                    .setSubscriptionId(subscription.getId());

            } else {
                responseSubscription = null;
            }

        } else {
            responseSubscription = null;
        }
        response.setSubscription(responseSubscription);
        return response;
    }
}