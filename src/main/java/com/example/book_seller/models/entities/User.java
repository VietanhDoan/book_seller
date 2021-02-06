package com.example.book_seller.models.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String password;
    private Date createdDate;

    public User(Integer id, String username, String phone, String email, String password, Date createdDate) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
    }

    public User() {
    }
}
