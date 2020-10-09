package com.yurisilva.mundialechallenge.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private Set<String> authorities;


    public static final class Builder {
        private Long id;
        private String name;
        private String username;
        private Set<String> authorities;

        private Builder() {
        }

        public static Builder anUserResponse() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withAuthorities(Set<String> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserResponse build() {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(id);
            userResponse.setName(name);
            userResponse.setUsername(username);
            userResponse.setAuthorities(authorities);
            return userResponse;
        }
    }
}
