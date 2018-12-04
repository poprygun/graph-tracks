package io.microsamples.ui.graphtracks.config;


import io.microsamples.ui.graphtracks.subscription.UpdatePublisher;
import io.microsamples.ui.graphtracks.subscription.amqp.AmqpTrackUpdatePublisher;
import io.microsamples.ui.graphtracks.subscription.amqp.Producer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@Profile("amqp")
@EnableScheduling
public class AmqpConfiguration {
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setDefaultReceiveQueue(tracksQueue().getName());
        template.setExchange(tracksExchange().getName());
        template.setRoutingKey("track");
        return template;
    }

    @Bean
    public Queue tracksQueue() {
        return new Queue("track_queue");
    }

    @Bean
    public Exchange tracksExchange() {
        return new FanoutExchange("track_exchange", false, true);
    }

    @Bean
    public Binding bnding() {
        return BindingBuilder.bind(tracksQueue()).to(tracksExchange()).with("track").noargs();
    }

    @Bean
    public Producer producer(RabbitTemplate rabbitTemplate){
        return new Producer(rabbitTemplate);
    }

    @Bean
    public UpdatePublisher trackUpdatePublisher(RabbitTemplate rabbitTemplate){
        return new AmqpTrackUpdatePublisher(rabbitTemplate);
    }
}
