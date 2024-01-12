package com.test.FeignClient;


import com.test.Entities.HotelManagementDTO;
import com.test.Entities.HotelManagementResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "HOTEL-MANAGEMENT-SERVICE",url = "http://localhost:8082", path = "/api/hotelManagement")
public interface ApiHotel {

    @PostMapping(value = "/saveHotel")
    public ResponseEntity<HotelManagementResponse> addHotel(@RequestBody HotelManagementDTO hotelManagementDTO);

    @GetMapping("/getAllHotels")
    public ResponseEntity<List<HotelManagementResponse>> getAllHotels();

    @GetMapping("/getHotel/{id}")
    public ResponseEntity<HotelManagementResponse> findByIdHotel(@PathVariable("id") String hotelId);

    @PutMapping("/updateHotel/{id}")
    public ResponseEntity<HotelManagementResponse> updateHotel(@PathVariable("id") String hotelId, @RequestBody HotelManagementDTO request);
}
