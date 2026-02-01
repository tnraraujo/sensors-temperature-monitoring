package com.tnr.sensors.sensors.temperature.monitoring.domain.service;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.TemperatureLog;
import com.tnr.sensors.sensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import com.tnr.sensors.sensors.temperature.monitoring.domain.repository.TemperatureLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class SensorMonitoringService {

    private final SensorMonitoringRepository sensorMonitoringRepository;
    private final TemperatureLogRepository temperatureLogRepository;

    @Transactional
    public void save(TemperatureLog temperatureLog) {

        sensorMonitoringRepository
                .findBySensorId(temperatureLog.getSensorId())
                .ifPresentOrElse(sm -> {

                    if (Boolean.TRUE.equals(sm.getEnabled())) {
                        sm.setUpdatedAt(OffsetDateTime.now());
                        sm.setLastTemperature(temperatureLog.getTemperature());
                        sensorMonitoringRepository.save(sm);

                        var newTemperatureLog = new TemperatureLog();
                        newTemperatureLog.setSensorId(temperatureLog.getSensorId());
                        newTemperatureLog.setTemperature(temperatureLog.getTemperature());
                        newTemperatureLog.setRegisteredAt(OffsetDateTime.now());
                        temperatureLogRepository.save(newTemperatureLog);
                        log.info("The temperature recorded successfully. {}", newTemperatureLog);

                    }  else {
                        log.info("The temperature could not be recorded because monitoring is disabled. {}", sm);
                    }
                }, () -> log.error("Sensor monitoring not found with id {}", temperatureLog.getSensorId()));
    }
}
