package io.microsamples.ui.graphtracks;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class TrackResolver implements GraphQLResolver<Track> {
    private TrackDao trackDao;

    public TrackResolver(TrackDao trackDao) {

        this.trackDao = trackDao;
    }
}
