package com.yurisilva.mundialechallenge.dto.resquest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String name;
    private String username;
    private String password;

}
