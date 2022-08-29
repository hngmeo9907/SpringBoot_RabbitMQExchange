package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanoutConfig {
    @Bean
    Queue marketingQueueFanout() {
        return new Queue("marketingQueueFanout", false);
    }

    @Bean
    Queue financeQueueFanout() {
        return new Queue("financeQueueFanout", false);
    }

    @Bean
    Queue adminQueueFanout() {
        return new Queue("adminQueueFanout", false);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    Binding marketingBindingFanout(Queue marketingQueueFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(marketingQueueFanout).to(exchange);
    }

    @Bean
    Binding financeBindingFanout(Queue financeQueueFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(financeQueueFanout).to(exchange);
    }

    @Bean
    Binding adminBindingFanout(Queue adminQueueFanout, FanoutExchange exchange) {
        return BindingBuilder.bind(adminQueueFanout).to(exchange);
    }
}
