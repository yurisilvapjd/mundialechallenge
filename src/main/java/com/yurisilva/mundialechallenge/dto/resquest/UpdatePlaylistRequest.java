package com.yurisilva.mundialechallenge.dto.resquest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePlaylistRequest extends CreatePlaylistRequest {

    private Long id;

}
