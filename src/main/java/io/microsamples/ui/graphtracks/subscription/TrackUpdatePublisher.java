package io.microsamples.ui.graphtracks.subscription;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Slf4j
public class TrackUpdatePublisher {
    private final Flowable<TrackUpdate> publisher;
    private static EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom();
    private final static Map<UUID, TrackUpdate> CURRENT_TRACKS = new ConcurrentHashMap<>();
    private final static Random rand = new Random();

    private final static List<UUID> tracksToFollow = Arrays.asList(
            UUID.fromString("94a0b5ef-462d-4f95-9512-60a0cc046aef")
            , UUID.fromString("cba6a98b-e1ce-485c-9efd-6ee9dc253fc8")
            , UUID.fromString("f641bc00-f1c7-44e7-8949-7fc9feb2e4f7")
            , UUID.fromString("da69671a-ec5c-41b8-8dca-0d5e7ce18b2c")
            , UUID.fromString("b3f1263e-c05d-4cd9-9bb5-f86109d33d52")
            , UUID.fromString("29e6b3cd-49b8-4aab-a946-b16004771a5d")
            , UUID.fromString("f04ef300-8547-49fa-b757-0a3f4ae632fa")
            , UUID.fromString("d7fd53dc-8d03-4fdb-98bf-604e1c723bf9")
            , UUID.fromString("3db281b6-e99d-4251-a57b-85b3e0cad838")
            , UUID.fromString("e7048b07-9c4c-4cba-9fb2-04c25c9be2dd")
    );


    public TrackUpdatePublisher() {
        Observable<TrackUpdate> trackUpdateObservable = Observable.create(emitter -> {

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newTrackUpdate(emitter), 0, 1, TimeUnit.SECONDS);

        });

        ConnectableObservable<TrackUpdate> connectableObservable = trackUpdateObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    static {
        for (UUID aTracksToFollow : tracksToFollow) {
            CURRENT_TRACKS.put(aTracksToFollow, enhancedRandom.nextObject(TrackUpdate.class));
        }
    }

    private Runnable newTrackUpdate(ObservableEmitter<TrackUpdate> emitter) {
        return () -> {
            List<TrackUpdate> trackUpdates = getUpdates(randomRoll(0, 10));
            if (trackUpdates != null) {
                emitTracks(emitter, trackUpdates);
            }
        };
    }

    private void emitTracks(ObservableEmitter<TrackUpdate> emitter, List<TrackUpdate> trackUpdates) {
        for (TrackUpdate trackUpdate : trackUpdates) {
            try {
                emitter.onNext(trackUpdate);
            } catch (RuntimeException e) {
                log.error("Cannot send TrackUpdate", e);
            }
        }
    }

    public Flowable<TrackUpdate> getPublisher() {
        return publisher;
    }

    public Flowable<TrackUpdate> getPublisher(List<String> trackIds) {
        if (trackIds != null) {
            return publisher.filter(trackUpdate -> trackIds.contains(trackUpdate.getId().toString()));
        }
        return publisher;
    }

    private List<TrackUpdate> getUpdates(int number) {
        List<TrackUpdate> updates = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            updates.add(rollUpdate());
        }
        return updates;
    }

    private TrackUpdate rollUpdate() {
        enhancedRandom.nextObject(TrackUpdate.class);
        return enhancedRandom.nextObject(TrackUpdate.class);
    }

    private static int randomRoll(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
