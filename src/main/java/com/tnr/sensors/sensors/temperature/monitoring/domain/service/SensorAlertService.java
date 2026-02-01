package com.tnr.sensors.sensors.temperature.monitoring.domain.service;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.TemperatureLog;
import com.tnr.sensors.sensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorAlertService {

    private final SensorAlertRepository sensorAlertRepository;

    public void handleSensorAlert(TemperatureLog temperatureLog) {

        sensorAlertRepository.findBySensorId(temperatureLog.getSensorId()).ifPresentOrElse(sensorAlert -> {

            if (sensorAlert.getMaxTemperature() != null
                    && temperatureLog.getTemperature().compareTo(sensorAlert.getMaxTemperature()) >= 0) {
                log.info("[ALERT MAX TEMPERATURE]: The maximum temperature is {} and the current temperature is {}", sensorAlert.getMaxTemperature(), temperatureLog.getTemperature());

            } else if (sensorAlert.getMinTemperature() != null
                    && temperatureLog.getTemperature().compareTo(sensorAlert.getMinTemperature()) <= 0) {
                log.info("[ALERT MIN TEMPERATURE]: The minimum temperature is {} and the current temperature is {}", sensorAlert.getMinTemperature(), temperatureLog.getTemperature());
            }

        }, () -> log.info("Temperature Log not found for Sensor Id: {}", temperatureLog.getSensorId()));
    }
}
