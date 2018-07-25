package com.example.demo.agent;

import com.example.demo.model.internal.request.CreateSubscriptionRequest;
import com.example.demo.model.internal.request.GetSubscriptionRequest;
import com.example.demo.model.internal.response.CreateSubscriptionResponse;
import com.example.demo.model.internal.response.GetSubscriptionResponse;

/**
 * @author eguzman (2018.07.20 4:01 PM)
 */
public interface SubscriptionAgent {
    CreateSubscriptionResponse createSubscription(CreateSubscriptionRequest request);

    GetSubscriptionResponse getSubscription(GetSubscriptionRequest request);
}
