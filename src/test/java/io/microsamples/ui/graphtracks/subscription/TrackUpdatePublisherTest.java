package io.microsamples.ui.graphtracks.subscription;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.Stream;

import static io.github.benas.randombeans.api.EnhancedRandom.randomStreamOf;

@Slf4j
public class TrackUpdatePublisherTest {

    private TrackUpdatePublisher trackUpdatePublisher = new TrackUpdatePublisher();

    @Test
    @Ignore
    public void randomTracks() throws InterruptedException {
       log.info("===>{}", trackUpdatePublisher.getPublisher()
               .subscribe(trackUpdate -> log.info("===> {}", trackUpdate)));

        Thread.currentThread().join();
    }
}