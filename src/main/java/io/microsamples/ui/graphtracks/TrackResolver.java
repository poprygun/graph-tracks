package io.microsamples.ui.graphtracks;


import graphql.kickstart.tools.GraphQLResolver;

public class TrackResolver implements GraphQLResolver<Track> {
    private TrackDao trackDao;

    public TrackResolver(TrackDao trackDao) {

        this.trackDao = trackDao;
    }
}
