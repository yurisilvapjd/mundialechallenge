package com.yurisilva.mundialechallenge.dto.resquest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSongRequest {

    private String name;

    private String artist;
}
