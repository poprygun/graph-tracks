package io.microsamples.ui.graphtracks;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Component
public class TrackDao {
    private TrackRepository repository;

    public List<Track> getTracks(int count, int offset) {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
