package com.yurisilva.mundialechallenge.service;

import com.yurisilva.mundialechallenge.dto.resquest.CreatePlaylistRequest;
import com.yurisilva.mundialechallenge.dto.resquest.UpdatePlaylistRequest;
import com.yurisilva.mundialechallenge.model.Playlist;
import com.yurisilva.mundialechallenge.model.Song;
import com.yurisilva.mundialechallenge.model.User;
import com.yurisilva.mundialechallenge.repository.PlaylistRepository;
import com.yurisilva.mundialechallenge.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PlaylistService {

    private final static Logger LOGGER = Logger.getLogger(PlaylistService.class.getName());

    private UserService userService;
    private SongRepository songRepository;
    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(UserService userService, SongRepository songRepository, PlaylistRepository playlistRepository) {
        this.userService = userService;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }

    public Playlist findByUserAndId(Long id) {
        LOGGER.info("Finding Playlist: " + id);
        return playlistRepository.findByUserAndId(getLoggedUser(), id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found."));
    }

    public List<Playlist> findByUserAndName(String name) {
        LOGGER.info("Finding Playlist: " + name);
        return playlistRepository.findByUserAndName(getLoggedUser(), name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found."));
    }

    public List<Playlist> findAllbyUser() {
        LOGGER.info("Finding all Playlists");
        return playlistRepository.findAllByUser(getLoggedUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found."));
    }

    public Playlist create(CreatePlaylistRequest request) {
        LOGGER.info("Playlist " + request.getName() + " has been created.");
        return playlistRepository.save(
                        Playlist.Builder.aPlaylist()
                        .withName(request.getName())
                        .withDescription(request.getDescription())
                        .withUser(getLoggedUser())
                        .build()
                );
    }

    public Playlist update(UpdatePlaylistRequest request) {
        Playlist playlist = this.fetchIfExists(request.getId());
        LOGGER.info("Playlist " + request.getName() + " has been updated.");
        return playlistRepository.save(
                        Playlist.Builder.aPlaylist()
                                .withId(playlist.getId())
                                .withName(request.getName())
                                .withDescription(request.getDescription())
                                .withUser(playlist.getUser())
                                .build()
                );
    }

    public void delete(Long id) {
        Playlist playlist = this.fetchIfExists(id);
        playlistRepository.delete(playlist);
        LOGGER.info("Playlist " + id + " has been deleted.");
    }

    public Playlist addSongToPlaylist(Long playlistId, Long songId) {
        User user = getLoggedUser();

        Playlist playlist = playlistRepository.findByUserAndId(user, playlistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found."));
        Song song = songRepository.findByUserAndId(user, songId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found."));
        playlist.getSongs().add(song);

        return playlistRepository.save(playlist);
    }

    public Playlist removeSongFromPlaylist(Long playlistId, Long songId) {

        User user = getLoggedUser();

        Playlist playlist = playlistRepository.findByUserAndId(user, playlistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found."));
        Song song = songRepository.findByUserAndId(user, songId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found."));

        if(playlist.getSongs().contains(song)){
            playlist.getSongs().remove(song);
            return playlistRepository.save(playlist);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Song does not belong to the given Playlist");
        }
    }

    private Playlist fetchIfExists(Long id){
        return playlistRepository.findByUserAndId(getLoggedUser(), id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found."));
    }

    private User getLoggedUser() {
        return userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
