package io.microsamples.ui.graphtracks;


import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class TracksBackendITest {

    @Autowired
    private TrackRepository repository;

    private static EasyRandom generator = new EasyRandom();

    final static int initialTracks = 5;


    @Test
    @Order(1)
    void addSomeTracks(){
        assertThat(repository.count()).isEqualTo(initialTracks); // loaded by application init

        final int loadedTracks = 13;
        final List<Track> expectedTracks = generator.objects(Track.class, loadedTracks).collect(Collectors.toList());

        for (Track expectedTrack : expectedTracks) {
            repository.save(expectedTrack);
        }

        assertThat(repository.count()).isEqualTo(initialTracks + loadedTracks);
    }


    @Test
    @Order(2)
    void rollbackVerify(){
        log.info("Verify that rollback took place.");
        assertThat(repository.count()).isEqualTo(initialTracks);
    }
}
