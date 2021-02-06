package com.example.book_seller.services;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.User;
import com.example.book_seller.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseMessage<String> register(User userEntity) {
        userRepository.save(userEntity);
        return new ResponseMessage<>(200, "register success", "");
    }
}
