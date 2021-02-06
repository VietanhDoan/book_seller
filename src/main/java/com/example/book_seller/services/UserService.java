package com.example.book_seller.services;

import com.example.book_seller.Utils.UtilsRegexEmail;
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

    public ResponseMessage<String> register(User user) {
        boolean isUserWithEmailCreated = userRepository.existsByEmail(user.getEmail());
        boolean isUserWithPhoneCreated = userRepository.existsByPhone(user.getPhone());
        boolean isEmailInvalid = UtilsRegexEmail.validate(user.getEmail());
        boolean isPasswordInvalid = user.getPassword().length() < 8 || user.getPassword().length() > 20;
        boolean isPhoneInvalid = user.getPhone().length() > 10;

        if (isUserWithEmailCreated) {
            return new ResponseMessage<>(401, "Error: Duplicate email with registered account", "");
        } else if (isUserWithPhoneCreated) {
            return new ResponseMessage<>(402, "Error: Duplicate phone with registered account", "");
        }
//        else if (isEmailInvalid) {
//            return new ResponseMessage<>(403, "Error: Email is invalid", "");
//        }
        else if (isPhoneInvalid) {
            return new ResponseMessage<>(404, "Error: Please input phone with shorted than 10 letters", "");
        }
        else if (isPasswordInvalid) {
            return new ResponseMessage<>(405, "Error: Please input password with 8-20 letters", "");
        } else {
            userRepository.save(user);
            return new ResponseMessage<>(200, "Success", "");
        }
    }
//
//    public ResponseMessage<String> login(User user) {
//        if (user.getPassword().length() < 8) {
//            return new ResponseMessage<>(401, "Error: Please input password ", "");
//        }
//        if (isUserWithInputEmailCreated) {
//            return new ResponseMessage<>(401, "Error: Duplicate email with registered account", "");
//        }
//        if (isUserWithInputPhoneCreated) {
//            return new ResponseMessage<>(402, "Error: Duplicate phone with registered account", "");
//        }
//        userRepository.save(user);
//        return new ResponseMessage<>(200, "Register success", "");
//    }
}
