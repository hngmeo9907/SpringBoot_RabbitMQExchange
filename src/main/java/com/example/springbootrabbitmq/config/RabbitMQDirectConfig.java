package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig {
    @Bean
    Queue marketingQueueDirect() {
        return new Queue("marketingQueueDirect", false);
    }

    @Bean
    Queue financeQueueDirect() {
        return new Queue("financeQueueDirect", false);
    }

    @Bean
    Queue adminQueueDirect() {
        return new Queue("adminQueueDirect", false);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    Binding marketingBindingDirect(Queue marketingQueueDirect, DirectExchange exchange) {
        return BindingBuilder.bind(marketingQueueDirect).to(exchange).with("marketing");
    }

    @Bean
    Binding financeBindingDirect(Queue financeQueueDirect, DirectExchange exchange) {
        return BindingBuilder.bind(financeQueueDirect).to(exchange).with("finance");
    }

    @Bean
    Binding adminBindingDirect(Queue adminQueueDirect, DirectExchange exchange) {
        return BindingBuilder.bind(adminQueueDirect).to(exchange).with("admin");
    }
}
