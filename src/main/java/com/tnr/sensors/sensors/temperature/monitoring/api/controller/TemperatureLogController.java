package com.tnr.sensors.sensors.temperature.monitoring.api.controller;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.TemperatureLog;
import com.tnr.sensors.sensors.temperature.monitoring.domain.repository.TemperatureLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sensors/{sensorId}/temperatures")
@RequiredArgsConstructor
public class TemperatureLogController {

    private final TemperatureLogRepository repository;

    @GetMapping
    public Page<TemperatureLog> search(@PathVariable String sensorId, Pageable pageable) {
        return repository.findAllBySensorId(sensorId, pageable);
    }
}
