package com.yurisilva.mundialechallenge.dto.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CredentialRequest {

    private String username;
    private String password;

}
