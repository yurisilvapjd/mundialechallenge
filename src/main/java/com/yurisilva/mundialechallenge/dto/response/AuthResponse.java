package com.yurisilva.mundialechallenge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }


    public static final class Builder {
        private String token;

        private Builder() {
        }

        public static Builder anAuthResponse() {
            return new Builder();
        }

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token);
        }
    }
}
