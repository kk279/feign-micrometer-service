package com.test.Models;

import lombok.Data;


@Data
public class UserDTO {
    private String userId;
    private String name;
    private String emails;
    private String about;
    private String hotelId;
}
