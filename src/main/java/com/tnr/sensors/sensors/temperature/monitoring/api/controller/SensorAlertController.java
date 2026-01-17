package com.tnr.sensors.sensors.temperature.monitoring.api.controller;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.SensorAlert;
import com.tnr.sensors.sensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/sensors/")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertRepository repository;

    @GetMapping("{sensorId}/alert/")
    public SensorAlert findById(@PathVariable String sensorId) {
        return repository.findById(sensorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public SensorAlert create(@RequestBody SensorAlert sensorAlert) {
        var alert = SensorAlert.builder()
                .id(sensorAlert.getId())
                .maxTemperature(sensorAlert.getMaxTemperature())
                .minTemperature(sensorAlert.getMinTemperature())
                .build();
        return repository.save(alert);
    }

    @PutMapping("{sensorId}/alert")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String sensorId, SensorAlert sensorAlert) {
        var alert = repository.findById(sensorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        alert.setMaxTemperature(sensorAlert.getMaxTemperature());
        alert.setMinTemperature(sensorAlert.getMinTemperature());
        repository.save(alert);
    }

    @DeleteMapping("{sensorId}/alert")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String sensorId) {
        var alert = repository.findById(sensorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(alert);
    }
}
