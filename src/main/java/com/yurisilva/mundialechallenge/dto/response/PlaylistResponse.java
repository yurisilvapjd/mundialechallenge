package com.yurisilva.mundialechallenge.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaylistResponse {

    private Long id;

    private String name;

    private String description;

    private List<SongResponse> songs;


    public static final class Builder {
        private Long id;
        private String name;
        private String description;
        private List<SongResponse> songs;

        private Builder() {
        }

        public static Builder aPlaylistResponse() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withSongs(List<SongResponse> songs) {
            this.songs = songs;
            return this;
        }

        public PlaylistResponse build() {
            PlaylistResponse playlistResponse = new PlaylistResponse();
            playlistResponse.setId(id);
            playlistResponse.setName(name);
            playlistResponse.setDescription(description);
            playlistResponse.setSongs(songs);
            return playlistResponse;
        }
    }
}
