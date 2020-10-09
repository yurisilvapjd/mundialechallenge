package com.yurisilva.mundialechallenge.rest.authentication;


import com.yurisilva.mundialechallenge.dto.response.AuthResponse;
import com.yurisilva.mundialechallenge.dto.resquest.CredentialRequest;
import com.yurisilva.mundialechallenge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/")
public class AuthenticationResource {

    private final AuthenticationService service;

    @Autowired
    public AuthenticationResource(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("authenticate")
    public AuthResponse authenticate(
            @RequestBody CredentialRequest credentialRequest,
            HttpServletRequest request) {
        return service.authenticate(credentialRequest, request);
    }
}
