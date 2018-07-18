package com.example.demo.model.internal.request;

/**
 * @author eguzman (2018.07.09 2:43 PM)
 */
public class GetCustomerRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public GetCustomerRequest setId(Long id) {
        this.id = id;
        return this;
    }
}
