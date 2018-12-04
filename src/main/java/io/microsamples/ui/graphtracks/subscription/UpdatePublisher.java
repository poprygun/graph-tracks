package io.microsamples.ui.graphtracks.subscription;

import io.reactivex.Flowable;

import java.util.List;

public interface UpdatePublisher {
    Flowable<TrackUpdate> getPublisher();

    Flowable<TrackUpdate> getPublisher(List<String> trackIds);
}
