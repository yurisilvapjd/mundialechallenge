package com.yurisilva.mundialechallenge.mapper;

import com.yurisilva.mundialechallenge.dto.response.UserResponse;
import com.yurisilva.mundialechallenge.model.Authority;
import com.yurisilva.mundialechallenge.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.Builder.anUserResponse()
                .withId(user.getId())
                .withName(user.getName())
                .withUsername(user.getUsername())
                .withAuthorities(getAuthorities(user))
                .build();
    }

    public List<UserResponse> toResponse(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user ->
                userResponses.add(
                        UserResponse.Builder.anUserResponse()
                                .withId(user.getId())
                                .withName(user.getName())
                                .withUsername(user.getUsername())
                                .withAuthorities(getAuthorities(user))
                                .build()
                ));
        return userResponses;
    }

    private Set<String> getAuthorities(User user) {
        return user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
    }
}
