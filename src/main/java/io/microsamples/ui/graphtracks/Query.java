package io.microsamples.ui.graphtracks;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {

    private TrackDao trackDao;

    public List<Track> allTracks(int count, int offset){
        return trackDao.getTracks(count, offset);
    }
}
