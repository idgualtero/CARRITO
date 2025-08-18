package com.usabana.models.synchronous;

import lombok.Data;

@Data
public class AuthorizationRequest {

    private int id;
    private String productName;
    private double totalAmtt;
    private String paymentMethod;
    private int quantity;
    private String shipDate;
    private boolean complete;

}