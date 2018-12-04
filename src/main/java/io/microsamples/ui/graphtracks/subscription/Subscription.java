package io.microsamples.ui.graphtracks.subscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

import java.util.List;

@Slf4j
public class Subscription implements GraphQLSubscriptionResolver {

    private UpdatePublisher updatePublisher;

    public Subscription(UpdatePublisher trackUpdatePublisher) {
        this.updatePublisher = trackUpdatePublisher;
    }

    public Publisher<TrackUpdate> changedTrack(List<String> trackIds) {
        return updatePublisher.getPublisher(trackIds);
    }
}
