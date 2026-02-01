package com.tnr.sensors.sensors.temperature.monitoring.api.controller;

import com.tnr.sensors.sensors.temperature.monitoring.api.model.SensorAlertInput;
import com.tnr.sensors.sensors.temperature.monitoring.domain.model.SensorAlert;
import com.tnr.sensors.sensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/sensors")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertRepository repository;

    @GetMapping("/{sensorId}/alert")
    public SensorAlert findById(@PathVariable UUID sensorId) {
        return repository.findBySensorId(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{sensorId}/alert")
    public void create(@PathVariable UUID sensorId, @RequestBody SensorAlertInput sensorAlertInput) {

        repository.findBySensorId(sensorId).ifPresentOrElse(sensorAlert -> {
            log.info("There is sensor alert config for sensorId={}", sensorAlert.getId());
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }, () -> repository.save(SensorAlert.builder()
                .sensorId(sensorId)
                .maxTemperature(sensorAlertInput.getMaxTemperature())
                .minTemperature(sensorAlertInput.getMinTemperature())
                .build()));
    }

    @PutMapping("/{sensorId}/alert")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID sensorId, SensorAlert sensorAlert) {
        var alert = repository.findBySensorId(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        alert.setMaxTemperature(sensorAlert.getMaxTemperature());
        alert.setMinTemperature(sensorAlert.getMinTemperature());
        repository.save(alert);
    }

    @DeleteMapping("/{sensorId}/alert")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID sensorId) {

        var alert = repository.findBySensorId(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(alert);
    }
}
