package com.example.book_seller.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user")
@Getter @Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name = "username")
    String username;
    @Column(name = "phone")
    String phone;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "created_date")
    Date createdDate;

    public UserEntity(Integer id, String username, String phone, String email, String password, Date createdDate) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
    }

    public UserEntity() {
    }
}
