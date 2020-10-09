package com.yurisilva.mundialechallenge.rest.api;

import com.yurisilva.mundialechallenge.dto.response.PlaylistResponse;
import com.yurisilva.mundialechallenge.dto.resquest.CreatePlaylistRequest;
import com.yurisilva.mundialechallenge.dto.resquest.UpdatePlaylistRequest;
import com.yurisilva.mundialechallenge.mapper.PlaylistMapper;
import com.yurisilva.mundialechallenge.service.PlaylistService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/protected/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;
    private PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistResource(PlaylistService playlistService, PlaylistMapper playlistMapper) {
        this.playlistService = playlistService;
        this.playlistMapper = playlistMapper;
    }


    @ApiOperation("Finds a Playlist by its id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaylistResponse findByUserAndId(@PathVariable("id") Long id) {
        return playlistMapper.toResponse(playlistService.findByUserAndId(id));
    }

    @ApiOperation("Finds a Playlist by its name")
    @GetMapping("/withname/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<PlaylistResponse> findByUserAndName(@PathVariable("name") String name) {
        return playlistMapper.toResponse(playlistService.findByUserAndName(name));
    }

    @ApiOperation("Finds all Playlists")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PlaylistResponse> findAll() {
        return playlistMapper.toResponse(playlistService.findAllbyUser());
    }

    @ApiOperation("Creates a Playlist")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistResponse create(@RequestBody CreatePlaylistRequest request) {
        return playlistMapper.toResponse(playlistService.create(request));
    }

    @ApiOperation("Updates a Playlist")
    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public PlaylistResponse update(@RequestBody UpdatePlaylistRequest request) {
        return playlistMapper.toResponse(playlistService.update(request));
    }

    @ApiOperation("Deletes a Playlist by its id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        playlistService.delete(id);
    }

    @ApiOperation("Adds a Song to a Playlist by theirs id")
    @PatchMapping("/add/{songId}/to/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    public PlaylistResponse addSongToPlaylist(@PathVariable Long playlistId, Long songId) {
        return playlistMapper.toResponse(playlistService.addSongToPlaylist(playlistId, songId));
    }

    @ApiOperation("Remove a Song from a Playlist by theirs id")
    @PatchMapping("/remove/{songId}/from/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    public PlaylistResponse removeSongFromPlaylist(@PathVariable Long playlistId, Long songId) {
        return playlistMapper.toResponse(playlistService.removeSongFromPlaylist(playlistId, songId));
    }

}
