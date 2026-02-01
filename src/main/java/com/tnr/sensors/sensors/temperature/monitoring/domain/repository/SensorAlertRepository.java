package com.tnr.sensors.sensors.temperature.monitoring.domain.repository;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.SensorAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SensorAlertRepository extends JpaRepository<SensorAlert, UUID> {
    Optional<SensorAlert> findBySensorId(UUID sensorId);
}
