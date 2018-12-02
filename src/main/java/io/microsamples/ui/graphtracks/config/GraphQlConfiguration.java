package io.microsamples.ui.graphtracks.config;

import io.microsamples.ui.graphtracks.Query;
import io.microsamples.ui.graphtracks.Track;
import io.microsamples.ui.graphtracks.TrackDao;
import io.microsamples.ui.graphtracks.subscription.Subscription;
import io.microsamples.ui.graphtracks.subscription.TrackUpdatePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.benas.randombeans.api.EnhancedRandom.randomStreamOf;

@Configuration
public class GraphQlConfiguration {
    @Bean
    public TrackDao postDao() {
        Stream<Track> trackStream = randomStreamOf(10000, Track.class);
        return new TrackDao(trackStream.collect(Collectors.toList()));
    }

    @Bean
    public Query query(TrackDao trackDao){
        return new Query(trackDao);
    }

    @Bean
    public Subscription subscription(TrackUpdatePublisher trackUpdatePublisher){
        return new Subscription(trackUpdatePublisher);
    }

    @Bean
    public TrackUpdatePublisher trackUpdatePublisher(){
        return new TrackUpdatePublisher();
    }

}
