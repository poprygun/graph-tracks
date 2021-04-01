package io.microsamples.ui.graphtracks.subscription;

import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class Subscription implements GraphQLSubscriptionResolver {

    private UpdatePublisher updatePublisher;

    public Publisher<TrackUpdate> changedTrack(List<String> trackIds) {
        return updatePublisher.getPublisher(trackIds);
    }
}
