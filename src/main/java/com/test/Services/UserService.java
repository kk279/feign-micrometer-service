package com.test.Services;
import com.test.Models.UserDTO;
import com.test.Models.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse addUser(UserDTO userDTO);
    List<UserResponse> getAllUsers();
    UserResponse getUser(String id);
    UserResponse updateUser(String id,UserDTO request);




}
