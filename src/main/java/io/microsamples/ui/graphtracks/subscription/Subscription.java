package io.microsamples.ui.graphtracks.subscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Subscription implements GraphQLSubscriptionResolver {

    private TrackUpdatePublisher TrackUpdatePublisher;

    Subscription(TrackUpdatePublisher TrackUpdatePublisher) {
        this.TrackUpdatePublisher = TrackUpdatePublisher;
    }

    public Publisher<TrackUpdate> getTracksChanged(List<String> trackIds) {
        return TrackUpdatePublisher.getPublisher(trackIds);
    }
}
