package com.test.Models;

import com.test.Entities.HotelManagementResponse;
import lombok.Data;


@Data
public class UserResponse {
    private String userId;
    private String name;
    private String emails;
    private String about;
    private HotelManagementResponse hotelManagementResponse;
}
