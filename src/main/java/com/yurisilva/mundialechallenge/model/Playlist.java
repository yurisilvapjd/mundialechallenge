package com.yurisilva.mundialechallenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "tbl_playlist")
@Getter
@Setter
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "description", length = 150)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "tbl_playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> songs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public static final class Builder {
        private Long id;
        private String name;
        private String description;
        private Set<Song> songs;
        private User user;

        private Builder() {
        }

        public static Builder aPlaylist() {
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

        public Builder withSongs(Set<Song> songs) {
            this.songs = songs;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Playlist build() {
            Playlist playlist = new Playlist();
            playlist.setId(id);
            playlist.setName(name);
            playlist.setDescription(description);
            playlist.setSongs(songs);
            playlist.setUser(user);
            return playlist;
        }
    }
}
