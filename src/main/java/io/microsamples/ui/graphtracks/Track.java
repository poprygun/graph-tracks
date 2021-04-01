package io.microsamples.ui.graphtracks;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Track {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private double latitude;
    private double longitude;
}
