package com.yurisilva.mundialechallenge.dto.resquest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSongRequest extends CreateSongRequest {

    private Long id;
}
