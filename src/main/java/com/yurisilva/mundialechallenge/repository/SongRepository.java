package com.yurisilva.mundialechallenge.repository;

import com.yurisilva.mundialechallenge.model.Song;
import com.yurisilva.mundialechallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findByUserAndId(User user, Long id);

    Optional<List<Song>> findByUserAndName(User user, String name);

    Optional<List<Song>> findAllByUser(User user);
}
