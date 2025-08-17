package com.usabana.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public RabbitMqPublisher(RabbitTemplate rabbitTemplate,
                             @Value("${rabbitmq.exchange}") String exchange,
                             @Value("${rabbitmq.routing-key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void enviarMensaje(String mensaje) {
        rabbitTemplate.convertAndSend(exchange, routingKey, mensaje);
        System.out.println("Mensaje enviado a RabbitMQ: " + mensaje);
    }
}
