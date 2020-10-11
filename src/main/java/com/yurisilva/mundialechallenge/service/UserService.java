package com.yurisilva.mundialechallenge.service;

import com.yurisilva.mundialechallenge.dto.resquest.CreateUserRequest;
import com.yurisilva.mundialechallenge.dto.resquest.UpdateUserRequest;
import com.yurisilva.mundialechallenge.enumerations.Role;
import com.yurisilva.mundialechallenge.model.Authority;
import com.yurisilva.mundialechallenge.model.User;
import com.yurisilva.mundialechallenge.repository.AuthorityRepository;
import com.yurisilva.mundialechallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.logging.Logger;

@Service
public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public User findByUsername(String username) {
        LOGGER.info("Finding User " + username);
        return userRepository.findOneByUsernameIgnoreCase(username)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }

    public User findById(Long id) {
        LOGGER.info("Finding User " + id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }

    public List<User> findAll() {
        LOGGER.info("Finding all Users");
        return userRepository.findAll();
    }

    public User create(CreateUserRequest request) {
        if(alreadyExists(request.getUsername())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already used.");

        LOGGER.info("User " + request.getName() + " has been created.");

        Authority authority = authorityRepository.findOneByName(Role.ROLE_USER.toString());

        User user = userRepository.save(
                User.Builder.anUser()
                        .withName(request.getName())
                        .withUsername(request.getUsername())
                        .withPassword(new BCryptPasswordEncoder().encode(request.getPassword()))
                        .build());

        user.setAuthorities(new HashSet<>(Arrays.asList(authority)));

        return userRepository.save(user);
    }

    public User update(UpdateUserRequest request) {
        if(isItYourself(request.getId())){
            LOGGER.info("User " + request.getName() + " has been updated.");
            return userRepository.save(
                    User.Builder.anUser()
                            .withId(request.getId())
                            .withName(request.getName())
                            .withUsername(request.getUsername())
                            .withPassword(new BCryptPasswordEncoder().encode(request.getPassword()))
                            .build()
            );
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This is not your User Id.");
        }
    }

    public void delete(Long id) {
        if(isItYourself(id)){
            userRepository.delete(this.findById(id));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This is not your User Id.");
        }
        LOGGER.info("User " + id + " has been deleted.");
    }

    private boolean alreadyExists(String username){
        return userRepository.findOneByUsernameIgnoreCase(username)
                .isPresent();
    }

    private boolean isItYourself(Long id){
        return id.equals(getLoggedUser().getId());
    }

    private User getLoggedUser() {
        return this.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
