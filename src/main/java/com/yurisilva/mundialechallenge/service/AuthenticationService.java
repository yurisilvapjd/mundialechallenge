package com.yurisilva.mundialechallenge.service;

import com.yurisilva.mundialechallenge.configuration.DomainUserDetailsService;
import com.yurisilva.mundialechallenge.configuration.TokenGenerator;
import com.yurisilva.mundialechallenge.dto.response.AuthResponse;
import com.yurisilva.mundialechallenge.dto.response.UserResponse;
import com.yurisilva.mundialechallenge.dto.resquest.CredentialRequest;
import com.yurisilva.mundialechallenge.mapper.UserMapper;
import com.yurisilva.mundialechallenge.model.User;
import com.yurisilva.mundialechallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {

    private final AuthenticationManagerBuilder auth;
    private final DomainUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationService(AuthenticationManagerBuilder auth,
                                 DomainUserDetailsService userDetailsService,
                                 UserRepository userRepository,
                                 UserMapper userMapper) {
        this.auth = auth;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public AuthResponse authenticate(CredentialRequest credential, HttpServletRequest request) {
        User user = userRepository.findOneByUsernameIgnoreCase(credential.getUsername())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Unable to authenticate. Invalid User."));

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        if (!bCrypt.matches(credential.getPassword(), user.getPassword())) {
            throw new AuthenticationCredentialsNotFoundException("Unable to authenticate. Invalid Password.");
        }

        UsernamePasswordAuthenticationToken authentication = setUserInContext(credential.getUsername(), request);

        if (authentication.isAuthenticated()) {
            UserResponse userResponse = userMapper.toResponse(user);
            String token = new TokenGenerator().generateToken(userResponse);
            return new AuthResponse(token);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "An error occurred while trying to authenticate.");
        }
    }

    public UsernamePasswordAuthenticationToken setUserInContext(String username, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
