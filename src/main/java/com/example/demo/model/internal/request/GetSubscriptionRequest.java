package com.example.demo.model.internal.request;

/**
 * @author eguzman (2018.07.20 1:41 PM)
 */
public class GetSubscriptionRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public GetSubscriptionRequest setId(Long id) {
        this.id = id;
        return this;
    }
}
