package io.microsamples.ui.graphtracks.subscription.amqp;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.microsamples.ui.graphtracks.subscription.TrackUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private static EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom();

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void produce()
    {
        TrackUpdate track = enhancedRandom.nextObject(TrackUpdate.class);
        log.info(" Publishing track {} ", track);
        rabbitTemplate.convertAndSend(track);
    }
}
