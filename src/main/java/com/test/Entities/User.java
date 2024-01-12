package com.test.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "micro_user")
public class User {
    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAILS")
    private String emails;
    @Column(name = "ABOUT")
    private String about;
    @Column(name = "HotelId")
    private String hotelId;

}
