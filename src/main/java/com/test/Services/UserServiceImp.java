package com.test.Services;
import com.test.FeignClient.ApiHotel;
import com.test.Entities.HotelManagementResponse;
import com.test.Entities.User;
import com.test.Models.UserDTO;
import com.test.Models.UserResponse;
import com.test.Repositories.UserRepository;
import com.test.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository  userRepository;


    @Autowired
    ApiHotel apiHotel;

    @Override
    public UserResponse addUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setEmails(userDTO.getEmails());
        user.setAbout(userDTO.getAbout());
        user.setHotelId(userDTO.getHotelId());
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(savedUser.getUserId());
        userResponse.setName(savedUser.getName());
        userResponse.setEmails(savedUser.getEmails());
        userResponse.setAbout(savedUser.getAbout());

        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Calling get all method User Service ...");
        List<User> fetchedUsers = userRepository.findAll();
        List<UserResponse>  userResponseList = new ArrayList<>();
        for (User user : fetchedUsers){
            UserResponse userResponse = new UserResponse();
            userResponse.setUserId(user.getUserId());
            userResponse.setName(user.getName());
            userResponse.setEmails(user.getEmails());
            userResponse.setAbout(user.getAbout());
            ResponseEntity<HotelManagementResponse> hotelManagementResponseResponseEntity = apiHotel.findByIdHotel(user.getHotelId());
            HotelManagementResponse hotelManagementResponse = mapToHotelManagementResponse(hotelManagementResponseResponseEntity);
            userResponse.setHotelManagementResponse(hotelManagementResponse);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @Override
    public UserResponse getUser(String userId) {
        User fetchedByIdUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id is not found on server !!"+ userId));
       UserResponse userResponse = new UserResponse();
       userResponse.setUserId(fetchedByIdUser.getUserId());
       userResponse.setName(fetchedByIdUser.getName());
       userResponse.setEmails(fetchedByIdUser.getEmails());
       userResponse.setAbout(fetchedByIdUser.getAbout());
       ResponseEntity<HotelManagementResponse> hotelManagementResponseResponseEntity = apiHotel.findByIdHotel(fetchedByIdUser.getHotelId());
       HotelManagementResponse hotelManagementResponse = mapToHotelManagementResponse(hotelManagementResponseResponseEntity);
       userResponse.setHotelManagementResponse(hotelManagementResponse);
       return userResponse;
    }

    public HotelManagementResponse mapToHotelManagementResponse(ResponseEntity<HotelManagementResponse> hotelManagementResponseResponseEntity) {
        HotelManagementResponse hotelManagementResponse = new HotelManagementResponse();
        hotelManagementResponse.setId(hotelManagementResponseResponseEntity.getBody().getId());
        hotelManagementResponse.setName(hotelManagementResponseResponseEntity.getBody().getName());
        hotelManagementResponse.setLocations(hotelManagementResponseResponseEntity.getBody().getLocations());
        hotelManagementResponse.setAbout(hotelManagementResponseResponseEntity.getBody().getAbout());
        return hotelManagementResponse;
    }

    @Override
    public UserResponse updateUser(String userId, UserDTO request) {
        User fechedByIdUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id is not found on server !!"+ userId));
        fechedByIdUser.setUserId(request.getUserId());
        fechedByIdUser.setName(request.getName());
        fechedByIdUser.setEmails(request.getEmails());
        fechedByIdUser.setAbout(request.getAbout());
        fechedByIdUser.setHotelId(request.getHotelId());
        User updatedUser = userRepository.save(fechedByIdUser);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(updatedUser.getUserId());
        userResponse.setName(updatedUser.getName());
        userResponse.setEmails(updatedUser.getEmails());
        userResponse.setAbout(updatedUser.getAbout());
        ResponseEntity<HotelManagementResponse> hotelManagementResponseResponseEntity = apiHotel.findByIdHotel(updatedUser.getHotelId());
        HotelManagementResponse hotelManagementResponse = mapToHotelManagementResponse(hotelManagementResponseResponseEntity);
        userResponse.setHotelManagementResponse(hotelManagementResponse);
        return userResponse;
    }
}
