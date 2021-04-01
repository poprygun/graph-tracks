package io.microsamples.ui.graphtracks;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {

    List<Track> findByLatitude(Long lat);

    Track findById(UUID id);
}