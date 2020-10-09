package com.yurisilva.mundialechallenge.mapper;

import com.yurisilva.mundialechallenge.dto.response.PlaylistResponse;
import com.yurisilva.mundialechallenge.model.Playlist;
import com.yurisilva.mundialechallenge.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PlaylistMapper {

    private SongMapper songMapper;

    @Autowired
    public PlaylistMapper(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    public PlaylistResponse toResponse(Playlist playlist) {
        return PlaylistResponse.Builder.aPlaylistResponse()
                .withId(playlist.getId())
                .withName(playlist.getName())
                .withDescription(playlist.getDescription())
                .withSongs(songMapper.toResponse(playlist.getSongs() != null ? playlist.getSongs() : new HashSet<Song>()))
                .build();

    }

    public List<PlaylistResponse> toResponse(List<Playlist> playlists) {
        List<PlaylistResponse> songResponseList = new ArrayList<>();
        playlists.forEach(playlist -> songResponseList.add(
                PlaylistResponse.Builder.aPlaylistResponse()
                        .withId(playlist.getId())
                        .withName(playlist.getName())
                        .withDescription(playlist.getDescription())
                        .withSongs(songMapper.toResponse(playlist.getSongs() != null ? playlist.getSongs() : new HashSet<Song>()))
                        .build()
        ));
        return songResponseList;
    }
}
