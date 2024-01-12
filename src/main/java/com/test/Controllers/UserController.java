package com.test.Controllers;

import com.test.Models.UserDTO;
import com.test.Models.UserResponse;
import com.test.Services.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/User")
@Slf4j
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("/adduser")
    private ResponseEntity<UserResponse> saveUser(@RequestBody UserDTO userDTO) {
        UserResponse userResponse = userServiceImp.addUser(userDTO);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    private ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Calling get all endpoint User Controller ...");
        List<UserResponse> userResponse = userServiceImp.getAllUsers();
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @GetMapping("/findByIdUser/{userId}")
    private ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {
        UserResponse userResponse = userServiceImp.getUser(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{userId}")
    private ResponseEntity<UserResponse> updateUser(@PathVariable("userId") String UserId,@RequestBody UserDTO request) {
        UserResponse userResponse = userServiceImp.updateUser(UserId,request);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
