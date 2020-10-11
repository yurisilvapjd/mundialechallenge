package com.yurisilva.mundialechallenge.dto.resquest;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdatePlaylistRequest extends CreatePlaylistRequest {

    @NotNull
    private Long id;

}
