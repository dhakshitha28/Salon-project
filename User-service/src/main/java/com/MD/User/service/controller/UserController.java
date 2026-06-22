package com.MD.User.service.controller;

import com.MD.User.service.Service.UserService;
import com.MD.User.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/api/users")
    public ResponseEntity <User> createUser(@RequestBody @Validated User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/api/users/{UserId}")
    public ResponseEntity<User> getUserById(@PathVariable("UserId")Long id) throws Exception {
        User user=userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);}


    @PutMapping("/api/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Long userId) throws Exception {
        User updateUser=userService.updateUser(user,userId);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) throws Exception {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
    }
}

