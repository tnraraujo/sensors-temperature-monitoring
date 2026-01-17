package com.tnr.sensors.sensors.temperature.monitoring.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureLog {

    @Id
    private String id;

    private Double temperatureValue;
    private OffsetDateTime registeredAt;
    private String sensorId;
}
