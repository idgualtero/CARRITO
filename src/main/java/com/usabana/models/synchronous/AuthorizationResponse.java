package com.usabana.models.synchronous;

import lombok.Data;

@Data
public class AuthorizationResponse {

    private String code;
    private String message;

}