package com.example.book_seller.controllers;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.User;
import com.example.book_seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

//    final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<User>> postUser(@RequestBody User user) {
        userRepository.save(user);
        System.out.println("Hello");
        return ResponseEntity.ok(new ResponseMessage<>(200, "Post success: ", user));
    }
}
