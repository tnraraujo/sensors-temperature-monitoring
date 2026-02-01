package com.tnr.sensors.sensors.temperature.monitoring.api.controller;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.tnr.sensors.sensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/sensors/{sensorId}/monitoring")
@RequiredArgsConstructor
public class SensorMonitoringController {

    private final SensorMonitoringRepository repository;

    @GetMapping
    public SensorMonitoring findById(@PathVariable UUID sensorId) {

        var sensorMonitoring = repository
                .findBySensorId(sensorId)
                .orElse(new SensorMonitoring());

        if (Objects.isNull(sensorMonitoring.getSensorId())) {
            sensorMonitoring.setSensorId(sensorId);
            sensorMonitoring.setUpdatedAt(OffsetDateTime.now());
            return repository.save(sensorMonitoring);
        }
        return sensorMonitoring;
    }

    @PutMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable UUID sensorId) {

        var sensorMonitoring = repository.findBySensorId(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (Boolean.TRUE.equals(sensorMonitoring.getEnabled())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        sensorMonitoring.setEnabled(true);
        repository.save(sensorMonitoring);
    }

    @DeleteMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable UUID sensorId) {

        var sensorMonitoring = repository.findBySensorId(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (Boolean.FALSE.equals(sensorMonitoring.getEnabled())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        sensorMonitoring.setEnabled(false);
        repository.save(sensorMonitoring);
    }
}
