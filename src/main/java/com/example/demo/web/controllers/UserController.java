package com.example.demo.web.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.web.dtos.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="users", description = "사용자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 정보 없음") })
    public ResponseEntity<User> getUser(@Parameter(name = "userId", description = "사용자 ID", in = ParameterIn.PATH) @PathVariable Long userId)
    {
        return userService.getUserById(userId);
    }

    @PostMapping()
    @Operation(summary = "사용자 등록", description = "이름과 나이를 입력하여 사용자 정보를 등록합니다.")
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
