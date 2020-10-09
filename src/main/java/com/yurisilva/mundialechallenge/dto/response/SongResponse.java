package com.yurisilva.mundialechallenge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongResponse {

    private Long id;

    private String name;

    private String artist;


    public static final class Builder {

        private Long id;
        private String name;
        private String artist;

        private Builder() {
        }

        public static Builder aSongResponse() {
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

        public Builder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public SongResponse build() {
            SongResponse songResponse = new SongResponse();
            songResponse.setId(id);
            songResponse.setName(name);
            songResponse.setArtist(artist);
            return songResponse;
        }
    }

}
