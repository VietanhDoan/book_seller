package com.example.book_seller.models;

import com.example.book_seller.models.entities.User;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private Integer userID;

    public JwtResponse(String jwttoken, Integer userID) {
        this.jwttoken = jwttoken;
        this.userID = userID;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
