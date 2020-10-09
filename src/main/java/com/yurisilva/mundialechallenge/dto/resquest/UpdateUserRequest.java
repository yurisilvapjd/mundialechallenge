package com.yurisilva.mundialechallenge.dto.resquest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest extends CreateUserRequest {
    Long id;
}
