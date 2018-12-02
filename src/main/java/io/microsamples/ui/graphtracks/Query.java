package io.microsamples.ui.graphtracks;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private TrackDao trackDao;

    public Query(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    public List<Track> allTracks(int count, int offset){
        return trackDao.getTracks(count, offset);
    }
}
