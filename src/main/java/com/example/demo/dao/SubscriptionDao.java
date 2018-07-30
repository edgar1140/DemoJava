package com.example.demo.dao;

import com.example.demo.model.internal.Subscription;

/**
 * @author eguzman (2018.07.20 11:50 AM)
 */
public interface SubscriptionDao {
    boolean createSubscription(Long accountId, Long subscriptionId, Long createUserId, String firstName, String lastName,
                               String serialNumber, Boolean active, Long phoneNumber, String planName);

    Long getSubscriptionIdNextValue();

    Subscription getSubscriptionById(Long id);
}
