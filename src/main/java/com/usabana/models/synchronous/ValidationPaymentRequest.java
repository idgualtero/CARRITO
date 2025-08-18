package com.usabana.models.synchronous;

import lombok.Data;

@Data
public class ValidationPaymentRequest {

    private String document;
    private double amount;

}