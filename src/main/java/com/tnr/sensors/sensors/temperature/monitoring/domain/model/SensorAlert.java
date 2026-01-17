package com.tnr.sensors.sensors.temperature.monitoring.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorAlert {

    @Id
    private String id;

    private Double maxTemperature;
    private Double minTemperature;
}
