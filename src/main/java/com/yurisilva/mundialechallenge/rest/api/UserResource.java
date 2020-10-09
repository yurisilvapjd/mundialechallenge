package com.yurisilva.mundialechallenge.rest.api;

import com.yurisilva.mundialechallenge.dto.response.UserResponse;
import com.yurisilva.mundialechallenge.dto.resquest.CreateUserRequest;
import com.yurisilva.mundialechallenge.dto.resquest.UpdateUserRequest;
import com.yurisilva.mundialechallenge.mapper.UserMapper;
import com.yurisilva.mundialechallenge.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class UserResource {

    private final UserService service;
    private final UserMapper mapper;

    @Autowired
    public UserResource(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @ApiOperation("Finds a User by its Id")
    @GetMapping("/admin/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable("id") Long id) {
        return mapper.toResponse(service.findById(id));
    }

    @ApiOperation("Finds a User by its Username")
    @GetMapping("/admin/users/withusername/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findByUsername(@PathVariable("username") String username) {
        return mapper.toResponse(service.findByUsername(username));
    }

    @ApiOperation("Finds all Users")
    @GetMapping("/admin/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAll() {
        return mapper.toResponse(service.findAll());
    }

    @ApiOperation("Creates a User")
    @PostMapping("/admin/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody CreateUserRequest request) {
        return mapper.toResponse(service.create(request));
    }

    @ApiOperation("Updates a User")
    @PutMapping("/protected/users")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse update(@RequestBody UpdateUserRequest request) {
        return mapper.toResponse(service.update(request));
    }

    @ApiOperation("Deletes a User by its id")
    @DeleteMapping("/protected/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
