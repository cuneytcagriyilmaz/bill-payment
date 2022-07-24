package com.evam.billpayment.controller;


import com.evam.billpayment.dto.UserDTO;
import com.evam.billpayment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    //POST http://localhost:8080/users
    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody @Valid final UserDTO userDTO) {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }

    //GET http://localhost:8080/users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    //GET http://localhost:8080/users/{{userId}}
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final Long userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    //PUT http://localhost:8080/users/{{userId}}
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable final Long userId,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(userId, userDTO);
        return ResponseEntity.ok().build();
    }
    //DELETE http://localhost:8080/users/{{userId}}
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
