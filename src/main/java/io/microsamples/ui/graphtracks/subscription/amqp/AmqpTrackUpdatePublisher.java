package io.microsamples.ui.graphtracks.subscription.amqp;

import io.microsamples.ui.graphtracks.subscription.TrackUpdate;
import io.microsamples.ui.graphtracks.subscription.UpdatePublisher;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Slf4j
public class AmqpTrackUpdatePublisher implements UpdatePublisher {
    private final Flowable<TrackUpdate> publisher;
    private RabbitTemplate rabbitTemplate;

    public AmqpTrackUpdatePublisher( RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;

        Observable<TrackUpdate> trackUpdateObservable = Observable.create(emitter -> {

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newTrackUpdate(emitter), 0, 1, TimeUnit.SECONDS);

        });

        ConnectableObservable<TrackUpdate> connectableObservable = trackUpdateObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }


    private Runnable newTrackUpdate(ObservableEmitter<TrackUpdate> emitter) {
        return () -> {
            //todo this is blocking implementation - need to use reactive amqp consumer
            TrackUpdate trackUpdate = (TrackUpdate) rabbitTemplate.receiveAndConvert();
            log.info("Received track -> {}", trackUpdate);
            emitter.onNext(trackUpdate);

        };
    }

    @Override
    public Flowable<TrackUpdate> getPublisher() {
        return publisher;
    }

    public Flowable<TrackUpdate> getPublisher(List<String> trackIds) {
        //todo implement pagination
        return publisher;
    }

}
