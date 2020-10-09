package com.yurisilva.mundialechallenge.service;

import com.yurisilva.mundialechallenge.dto.resquest.CreateSongRequest;
import com.yurisilva.mundialechallenge.dto.resquest.UpdateSongRequest;
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
public class SongService {

    private final static Logger LOGGER = Logger.getLogger(SongService.class.getName());

    private UserService userService;
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;

    @Autowired
    public SongService(UserService userService, PlaylistRepository playlistRepository, SongRepository songRepository) {
        this.userService = userService;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    public List<Song> findByUserAndName(String name) {
        LOGGER.info("Finding Song: " + name);
        return songRepository.findByUserAndName(getLoggedUser(), name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found."));
    }

    public Song findByUserAndId(Long id) {
        LOGGER.info("Finding Song: " + id);
        return this.fecthIfExists(id);
    }

    public List<Song> findAllbyUser() {
        LOGGER.info("Finding all Song");
        return songRepository.findAllByUser(getLoggedUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found."));
    }

    public Song create(CreateSongRequest request) {
        LOGGER.info("Object " + request.getName() + " was created");
        return songRepository.save(
                        Song.Builder.aSong()
                                .withName(request.getName())
                                .withArtist(request.getArtist())
                                .withUser(getLoggedUser())
                                .build()
        );
    }

    public Song update(UpdateSongRequest request) {
        Song song = this.fecthIfExists(request.getId());
        LOGGER.info("Object " + request.getName() + " was updated");
        return songRepository.save(
                        Song.Builder.aSong()
                                .withId(song.getId())
                                .withName(request.getName())
                                .withArtist(request.getArtist())
                                .withUser(song.getUser())
                                .build()
        );
    }

    public void delete(Long id) {
        Song song = this.fecthIfExists(id);
        List<Playlist> playlists = song.getPlaylist();
        if(playlists == null || playlists.isEmpty()){
            songRepository.delete(song);
        } else {
           for(Playlist pl : playlists){
               pl.getSongs().remove(song);
               playlistRepository.save(pl);
           }
            songRepository.delete(song);
        }
        LOGGER.info("User " + id + " was deleted");
    }

    private Song fecthIfExists(Long id) {
        return songRepository.findByUserAndId(getLoggedUser(), id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found."));
    }

    private User getLoggedUser() {
        return userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
