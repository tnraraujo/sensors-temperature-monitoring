package com.tnr.sensors.sensors.temperature.monitoring.infra.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.json.JsonMapper;

import static com.tnr.sensors.sensors.temperature.monitoring.infra.config.RabbitMQNamesConfig.EXCHANGE_NAME;
import static com.tnr.sensors.sensors.temperature.monitoring.infra.config.RabbitMQNamesConfig.QUEUE_NAME;

@Configuration
public class RabbitMQConfig {

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter(JsonMapper jsonMapper) {
        return new JacksonJsonMessageConverter(jsonMapper);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(EXCHANGE_NAME).build();
    }
}
