package io.microsamples.ui.graphtracks.subscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

import java.util.List;

@Slf4j
public class Subscription implements GraphQLSubscriptionResolver {

    private TrackUpdatePublisher TrackUpdatePublisher;

    public Subscription(TrackUpdatePublisher TrackUpdatePublisher) {
        this.TrackUpdatePublisher = TrackUpdatePublisher;
    }

    public Publisher<TrackUpdate> changedTrack(List<String> trackIds) {
        return TrackUpdatePublisher.getPublisher(trackIds);
    }
}
