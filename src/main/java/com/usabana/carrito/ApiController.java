package com.usabana.carrito;

import com.usabana.models.asynchronous.CloudEvent;
import com.usabana.models.synchronous.AuthorizationRequest;
import com.usabana.models.synchronous.AuthorizationResponse;
import com.usabana.models.synchronous.ValidationPaymentRequest;
import com.usabana.models.synchronous.ValidationPaymentResponse;
import com.usabana.service.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final MessagePublisher publisher;
    private final List<AuthorizationRequest> authorizationRequests;
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/store/order")
    public ResponseEntity<AuthorizationResponse> publicar(@RequestBody AuthorizationRequest request) {
        System.out.println("Mensaje recibido: " + request.getId());
        AuthorizationResponse response = new AuthorizationResponse();
        response = enviarAutorizacion(request);
        if (response.getCode().equals("200")) {
            authorizationRequests.add(request);
            if (authorizationRequests.size() == 3) {
                publisher.sendCommand(generateCommand(authorizationRequests));
                authorizationRequests.clear();
            }
        }
        return ResponseEntity.ok(response);
    }

    public AuthorizationResponse enviarAutorizacion(AuthorizationRequest request) {
        String url = "https://aa76ec09-058b-4e39-8c52-3b382727e63c.mock.pstmn.io/validate-payment"; // Cambia la URL si
        // es externa
        ValidationPaymentRequest val = new ValidationPaymentRequest();
        val.setDocument(Integer.toString(request.getId()));
        val.setAmount(request.getTotalAmtt());

        ResponseEntity<ValidationPaymentResponse> valRes = restTemplate.postForEntity(
                url, val, ValidationPaymentResponse.class);
        AuthorizationResponse response = new AuthorizationResponse();
        if (valRes.getBody().getStatus().equals("success")) {
            response.setCode("200");
        } else {
            response.setCode("400");
        }

        response.setMessage(valRes.getBody().getMessage());
        return response;
    }

    private CloudEvent generateCommand(List<AuthorizationRequest> authorizationRequests) {
        return CloudEvent.builder()
                .specversion("1.0")
                .source("ms-shopping-cart")
                .subject("123")
                .id(UUID.randomUUID().toString())
                .time(new Date())
                .datacontenttype("application/json")
                .data(authorizationRequests)
                .build();
    }

}