package io.microsamples.ui.graphtracks;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.microsamples.ui.graphtracks.config.RandomizerConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@GraphQLTest
@Import({RandomizerConfiguration.class})
@Slf4j
class TrackEndpointTest {

    private EasyRandom easyRandom = new EasyRandom();

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private TrackDao trackDao;

    @Test
    @SneakyThrows
    void someTracks() {

        final List<Track> expectedTracks = easyRandom.objects(Track.class, 13).collect(Collectors.toList());

        when(trackDao.getTracks(anyInt(), anyInt())).thenReturn(expectedTracks);

        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/all-tracks.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.getList("$.data.allTracks", Track.class)).hasSizeGreaterThan(0);
    }
}