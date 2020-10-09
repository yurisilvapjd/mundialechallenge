package com.yurisilva.mundialechallenge.repository;

import com.yurisilva.mundialechallenge.model.Playlist;
import com.yurisilva.mundialechallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    Optional<Playlist> findByUserAndId(User user, Long id);

    Optional<List<Playlist>> findByUserAndName(User user, String name);

    Optional<List<Playlist>> findAllByUser(User user);
}
