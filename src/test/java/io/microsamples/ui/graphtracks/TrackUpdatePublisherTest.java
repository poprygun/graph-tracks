package io.microsamples.ui.graphtracks;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.microsamples.ui.graphtracks.config.RandomizerConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@GraphQLTest
@Import({RandomizerConfiguration.class})
@Slf4j
class TrackUpdatePublisherTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    @SneakyThrows
    void someTracks() {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/all-tracks.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.getList("$.data.allTracks", Track.class)).hasSizeGreaterThan(0);
    }
}