package com.tnr.sensors.sensors.temperature.monitoring.infra.config;

public class RabbitMQNamesConfig {

    private RabbitMQNamesConfig() {}

    public static final String QUEUE_NAME = "sensors-temperature-monitoring.v1.q";
    public static final String EXCHANGE_NAME = "sensors-temperature-processing.v1.e";
}
