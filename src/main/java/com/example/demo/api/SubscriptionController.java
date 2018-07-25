package com.example.demo.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.agent.CustomerAgent;
import com.example.demo.agent.SubscriptionAgent;
import com.example.demo.model.internal.Subscription;
import com.example.demo.model.internal.request.CreateCustomerRequest;
import com.example.demo.model.internal.request.CreateSubscriptionRequest;
import com.example.demo.model.internal.request.CreateUserRequest;
import com.example.demo.model.internal.request.GetCustomerRequest;
import com.example.demo.model.internal.request.GetSubscriptionRequest;
import com.example.demo.model.internal.response.CreateCustomerResponse;
import com.example.demo.model.internal.response.CreateSubscriptionResponse;
import com.example.demo.model.internal.response.GetCustomerResponse;
import com.example.demo.model.internal.response.GetSubscriptionResponse;
import com.example.demo.model.internal.response.GetUserResponse;

/**
 * @author eguzman (2018.07.20 3:54 PM)
 */
@RestController
@RequestMapping("subscription")
public class SubscriptionController {
    @Resource
        private SubscriptionAgent subscriptionAgent;
        @RequestMapping(value = "/create", method = RequestMethod.POST)
        public CreateSubscriptionResponse create (@RequestBody CreateSubscriptionRequest request){
            return subscriptionAgent.createSubscription(request);
        }
        @RequestMapping(value = "get-by-id",method = RequestMethod.POST)
        public GetSubscriptionResponse getSubscriptionById (@RequestBody GetSubscriptionRequest request) {
            return subscriptionAgent.getSubscription(request);
        }
}
