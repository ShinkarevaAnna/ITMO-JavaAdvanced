package com.example.car.controllers;


import com.example.car.model.dto.request.UserInfoRequest;
import com.example.car.model.dto.response.UserInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.car.constants.Constants.USERS_API;
import com.example.car.service.UserService;
@Tag(name = "Users")
@RestController
@RequestMapping(USERS_API)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create user")
    public UserInfoResponse createUser(@RequestBody @Valid UserInfoRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user by id")
    public UserInfoResponse updateUser(@PathVariable Long id, @RequestBody UserInfoRequest request) {
        return userService.updateUser(id, request);
    }
    @DeleteMapping("/{id}")
    @Operation( summary = "Delete user by id")
    public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    }
    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Not authorization"),
    })
    @Operation(summary = "Get user list")
    public List<UserInfoResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
