package com.usabana.carrito;

import com.usabana.models.AuthorizationRequest;
import com.usabana.models.AuthorizationResponse;
import com.usabana.models.ValidationPaymentRequest;
import com.usabana.models.ValidationPaymentResponse;
import com.usabana.service.RabbitMqPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    
     /*private final RabbitMqPublisher publisher;
     
     public ApiController(RabbitMqPublisher publisher) {
     this.publisher = publisher;
     }*/
     

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/store/order")
    public ResponseEntity<AuthorizationResponse> publicar(@RequestBody AuthorizationRequest request) {
        // publisher.enviarMensaje(mensaje);
        System.out.println("Mensaje recibido: " + request.getId());
        AuthorizationResponse response = new AuthorizationResponse();
        response = enviarAutorizacion(request);
        if (response.getCode().equals("200")) {
             //publisher.enviarMensaje("Mensaje recibido: " + request.getId());
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
                if (valRes.getBody().getStatus().equals("success")){                    
                    response.setCode("200");                
                }else { 
                    response.setCode("400");
                }
                
                response.setMessage(valRes.getBody().getMessage());
                return response;  
    }

}
