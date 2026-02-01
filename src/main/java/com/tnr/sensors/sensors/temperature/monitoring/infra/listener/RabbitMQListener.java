package com.tnr.sensors.sensors.temperature.monitoring.infra.listener;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.TemperatureLog;
import com.tnr.sensors.sensors.temperature.monitoring.domain.service.SensorAlertService;
import com.tnr.sensors.sensors.temperature.monitoring.domain.service.SensorMonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.tnr.sensors.sensors.temperature.monitoring.infra.config.RabbitMQNamesConfig.QUEUE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final SensorMonitoringService sensorMonitoringService;
    private final SensorAlertService sensorAlertService;

    @RabbitListener(queues = QUEUE_NAME, concurrency = "2-2")
    public void handle(@Payload TemperatureLog temperatureLog) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(5));
        log.info("Received temperature log: {}", temperatureLog);
        sensorMonitoringService.save(temperatureLog);
        sensorAlertService.handleSensorAlert(temperatureLog);
    }
}
