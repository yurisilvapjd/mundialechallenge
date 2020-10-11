package com.yurisilva.mundialechallenge.rest.api;

import com.yurisilva.mundialechallenge.dto.response.SongResponse;
import com.yurisilva.mundialechallenge.dto.resquest.CreateSongRequest;
import com.yurisilva.mundialechallenge.dto.resquest.UpdateSongRequest;
import com.yurisilva.mundialechallenge.mapper.SongMapper;
import com.yurisilva.mundialechallenge.service.SongService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/protected/songs")
public class SongResource {

    private SongService songService;
    private SongMapper songMapper;

    @Autowired
    public SongResource(SongService songService, SongMapper songMapper) {
        this.songService = songService;
        this.songMapper = songMapper;
    }

    @ApiOperation("Finds a Song by its id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SongResponse findByUserAndId(@PathVariable("id") Long id) {
        return songMapper.toResponse(songService.findByUserAndId(id));
    }

    @ApiOperation("Finds a Song by its name")
    @GetMapping("/withname/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<SongResponse> findByUserAndName(@PathVariable("name") String name) {
        return songMapper.toResponse(songService.findByUserAndName(name));
    }

    @ApiOperation("Finds all Songs")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<SongResponse> findAllByUser() {
        return songMapper.toResponse(songService.findAllbyUser());
    }

    @ApiOperation("Creates a Song")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public SongResponse create(@Valid @RequestBody CreateSongRequest request) {
        return songMapper.toResponse(songService.create(request));
    }

    @ApiOperation("Updates a Song")
    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public SongResponse update(@Valid @RequestBody UpdateSongRequest request) {
        return songMapper.toResponse(songService.update(request));
    }

    @ApiOperation("Deletes a Song by its id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        songService.delete(id);
    }

}
