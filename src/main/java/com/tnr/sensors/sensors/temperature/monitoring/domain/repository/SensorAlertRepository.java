package com.tnr.sensors.sensors.temperature.monitoring.domain.repository;

import com.tnr.sensors.sensors.temperature.monitoring.domain.model.SensorAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorAlertRepository extends JpaRepository<SensorAlert, String> {
}
