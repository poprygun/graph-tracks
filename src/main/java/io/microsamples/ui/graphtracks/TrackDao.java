package io.microsamples.ui.graphtracks;

import java.util.List;
import java.util.stream.Collectors;

public class TrackDao {
    private List<Track> tracks;

    public TrackDao(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks(int count, int offset) {
        return tracks.stream().skip(offset).limit(count).collect(Collectors.toList());
    }
}
