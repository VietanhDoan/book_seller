package com.example.book_seller.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseMessage(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage() {

    }
}
