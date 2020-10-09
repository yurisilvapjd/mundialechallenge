package com.yurisilva.mundialechallenge.repository;

import com.yurisilva.mundialechallenge.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findOneByName(String name);
}
