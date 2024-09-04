package com.example.car.controllers;


import com.example.car.model.dto.request.UserInfoRequest;
import com.example.car.model.dto.response.UserInfoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.example.car.constants.Constants.USER_API;

@RestController
@RequestMapping(USER_API)
public class UserController {
    @PostMapping
    public UserInfoResponse createUser(@RequestBody UserInfoRequest request) {
        return new UserInfoResponse();
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return new UserInfoResponse();
    }

    @PutMapping("/{id}")
    public UserInfoResponse updateUser(@PathVariable Long id, @RequestBody UserInfoRequest request) {
        return new UserInfoResponse();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

    }


    @GetMapping("/all")
    public List<UserInfoResponse> getAllUsers() {
        return Collections.singletonList(new UserInfoResponse());
    }
}
