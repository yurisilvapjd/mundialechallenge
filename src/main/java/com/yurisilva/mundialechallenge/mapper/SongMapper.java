package com.yurisilva.mundialechallenge.mapper;

import com.yurisilva.mundialechallenge.dto.response.SongResponse;
import com.yurisilva.mundialechallenge.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SongMapper {

    public SongResponse toResponse(Song song) {
        return SongResponse.Builder.aSongResponse()
                .withId(song.getId())
                .withName(song.getName())
                .withArtist(song.getArtist())
                .build();
    }

    public List<SongResponse> toResponse(List<Song> songs) {
        List<SongResponse> songResponseList = new ArrayList<>();
        songs.forEach(song -> songResponseList.add(SongResponse.Builder.aSongResponse()
                .withId(song.getId())
                .withName(song.getName())
                .withArtist(song.getArtist())
                .build()));
        return songResponseList;
    }

    public List<SongResponse> toResponse(Set<Song> songs) {
        List<SongResponse> songResponseList = new ArrayList<>();
        songs.forEach(song -> songResponseList.add(SongResponse.Builder.aSongResponse()
                .withId(song.getId())
                .withName(song.getName())
                .withArtist(song.getArtist())
                .build()));
        return songResponseList;
    }

}
