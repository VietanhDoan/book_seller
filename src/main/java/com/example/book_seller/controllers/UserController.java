package com.example.book_seller.controllers;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.User;
import com.example.book_seller.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

//    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> login(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
//
//    @PostMapping(path = "/logout", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> logout(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
//
//    @GetMapping(path = "/profile", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> getProfile(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
//
//    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> updateAccount(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
//
//    @DeleteMapping(path = "/delete", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> deleteAccount(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
}
