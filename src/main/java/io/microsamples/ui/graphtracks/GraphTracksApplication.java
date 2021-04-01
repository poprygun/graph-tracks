package io.microsamples.ui.graphtracks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
@Slf4j
public class GraphTracksApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphTracksApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TrackRepository repository) {
        return (args) -> {
            repository.save(new Track(UUID.randomUUID(), 0.13, 0.18));
            repository.save(new Track(UUID.randomUUID(), 0.14, 0.19));
            repository.save(new Track(UUID.randomUUID(), 0.15, 0.2));
            repository.save(new Track(UUID.randomUUID(), 0.16, 0.21));
            repository.save(new Track(UUID.randomUUID(), 0.17, 0.22));

            log.info("Loaded {} tracks", repository.count());
        };
    }
}
