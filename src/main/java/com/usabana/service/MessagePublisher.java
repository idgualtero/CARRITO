package com.usabana.service;

import com.usabana.models.asynchronous.CloudEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private static final String COMMAND_EXCHANGE = "DirectMessages";
    private static final String COMMAND_ROUTING_KEY = "ms-shopping-cart.orders.save";

    // Enviar Comando
    public void sendCommand(CloudEvent cloudEvent) {
        cloudEvent.setType(COMMAND_ROUTING_KEY);
        rabbitTemplate.convertAndSend(COMMAND_EXCHANGE, COMMAND_ROUTING_KEY, cloudEvent);
    }

}