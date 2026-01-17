package com.tnr.sensors.sensors.temperature.monitoring.api.controller;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.tnr.sensors.sensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("api/sensors/{sensorId}/monitoring")
@RequiredArgsConstructor
public class SensorMonitoringController {

    private final SensorMonitoringRepository repository;

    @GetMapping
    public SensorMonitoring findById(@PathVariable String sensorId) {
        var sensorMonitoring = repository.findById(sensorId).orElse(new  SensorMonitoring());
        if (sensorMonitoring.getId() == null) {
            sensorMonitoring.setId(sensorId);
            sensorMonitoring.setEnabled(false);
            sensorMonitoring.setLastTemperature(null);
            sensorMonitoring.setUpdateAt(OffsetDateTime.now());
        }
        return repository.save(sensorMonitoring);
    }

    @PutMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable String sensorId) {
        var sensorMonitoring = repository.findById(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (Boolean.TRUE.equals(sensorMonitoring.getEnabled())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        sensorMonitoring.setEnabled(true);
        repository.save(sensorMonitoring);
    }

    @DeleteMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable String sensorId) throws InterruptedException {
        var sensorMonitoring = repository.findById(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (Boolean.FALSE.equals(sensorMonitoring.getEnabled())) {
            Thread.sleep(Duration.ofSeconds(5));
        }

        sensorMonitoring.setEnabled(false);
        repository.save(sensorMonitoring);
    }
}
