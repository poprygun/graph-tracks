package io.microsamples.ui.graphtracks.subscription;

import io.github.benas.randombeans.annotation.Randomizer;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@ToString
public class TrackUpdate {
    public TrackUpdate(double latitude, double longitude, LocalDateTime dateTime) {
        id = UUID.randomUUID();
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Randomizer(TrackIdRandomizer.class)
    private UUID id;
    private double latitude;
    private double longitude;
    private final String dateTime;
}
