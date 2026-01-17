package com.tnr.sensors.sensors.temperature.monitoring.domain.repository;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, String> {
}
