package com.tnr.sensors.sensors.temperature.monitoring.domain.repository;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, UUID> {
    Optional<SensorMonitoring> findBySensorId(UUID sensorId);
}
