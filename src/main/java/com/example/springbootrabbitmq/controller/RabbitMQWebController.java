package com.example.springbootrabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbitmq/producer")
public class RabbitMQWebController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/direct")
    public String directProducer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                           @RequestParam("messageData") String messageData) {

        amqpTemplate.convertAndSend(exchange, routingKey, messageData);

        return "Message sent to the RabbitMQ Successfully";
    }

    @GetMapping(value = "/fanout")
    public String fanoutProducer(@RequestParam("exchangeName") String exchange,
                           @RequestParam("messageData") String messageData) {

        amqpTemplate.convertAndSend(exchange, "", messageData);

        return "Message sent to the RabbitMQ Fanout Exchange Successfully";
    }

    @GetMapping(value = "/topic")
    public String topicProducer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                           @RequestParam("messageData") String messageData) {

        amqpTemplate.convertAndSend(exchange, routingKey, messageData);

        return "Message sent to the RabbitMQ Topic Exchange Successfully";
    }

    @GetMapping(value = "/header")
    public String headerProducer(@RequestParam("exchangeName") String exchange, @RequestParam("department") String department,
                           @RequestParam("messageData") String messageData) {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("department", department);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(messageData, messageProperties);
        amqpTemplate.send(exchange, "", message);

        return "Message sent to the RabbitMQ Header Exchange Successfully";
    }
}
