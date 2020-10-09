package com.yurisilva.mundialechallenge.repository;

import com.yurisilva.mundialechallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsernameIgnoreCase(String username);
}
