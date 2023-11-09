package com.example.demo.web.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.web.dtos.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId)
    {
        return userService.getUserById(userId);
    }

    @PostMapping()
    public ResponseEntity<List<User>> createUsers(@RequestBody List<UserDTO> userDtos)
    {
        return userService.createUsers(userDtos);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDtos)
    {
        return userService.updateUser(userId, userDtos);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId)
    {
        return userService.deleteUserById(userId);
    }
}
