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
@NoArgsConstructor
@AllArgsConstructor
public class SensorMonitoring {

    @Id
    private String id;

    private Double lastTemperature;
    private OffsetDateTime updateAt;
    private Boolean enabled;
}
