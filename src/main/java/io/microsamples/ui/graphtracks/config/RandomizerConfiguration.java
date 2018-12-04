package io.microsamples.ui.graphtracks.config;

import io.microsamples.ui.graphtracks.subscription.TrackUpdatePublisher;
import io.microsamples.ui.graphtracks.subscription.UpdatePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!amqp")
public class RandomizerConfiguration {
    @Bean
    public UpdatePublisher trackUpdatePublisher(){
        return new TrackUpdatePublisher();
    }
}
