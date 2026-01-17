package com.tnr.sensors.sensors.temperature.monitoring;

import com.tnr.sensors.sensors.temperature.monitoring.common.IdGenerator;
import com.tnr.sensors.sensors.temperature.monitoring.common.UUIDv7Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

class UUIDv7Test {

    @Test
    void shouldGeneratorUUIDv7() {
        var uuid = IdGenerator.generateTimeBaseUUID();
        var uuidDateTime = UUIDv7Utils.extractOffsetDateTime(uuid).truncatedTo(ChronoUnit.MINUTES);
        var currentDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        Assertions.assertThat(uuidDateTime).isEqualTo(currentDateTime);
    }
}
