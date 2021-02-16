package com.example.book_seller.services;

import com.example.book_seller.Utils.UtilsRegexEmail;
import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.Book;
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
        String email = user.getEmail();
        String phone = user.getPhone();
        String password = user.getPassword();
        boolean isUserWithEmailCreated = userRepository.existsByEmail(email);
        boolean isUserWithPhoneCreated = userRepository.existsByPhone(phone);
        boolean isEmailInvalid = UtilsRegexEmail.validate(email);
        boolean isPasswordInvalid = password.length() < 8 || password.length() > 20;
        boolean isPhoneInvalid = phone.length() > 10;

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
            return new ResponseMessage<>(200, "Success - Register success", "" /*Access token*/);
        }
    }

    public ResponseMessage<String> login(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        boolean isPasswordInvalid = password.length() < 8 || password.length() > 20;
        if (isPasswordInvalid) {
            return new ResponseMessage<>(401, "Error: Please input password with 8-20 letters", "");
        }
        if (userRepository.existsByEmail(email) && userRepository.existsByPassword(password)) {
            return new ResponseMessage<>(200, "Success - Login success", ""/*Access token*/);
        }
        return new ResponseMessage<>(402, "Error: Account not registered", "");
    }
}
