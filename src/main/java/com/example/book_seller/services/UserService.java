package com.example.book_seller.services;

import com.example.book_seller.Utils.UtilsRegexEmail;
import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.User;
import com.example.book_seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
            return new ResponseMessage<>(401, "Error: Trùng email với tài khoản đã tồn tại", "");
        } else if (isUserWithPhoneCreated) {
            return new ResponseMessage<>(402, "Error: Trùng số điện thoại với tài khoản đã tồn tại", "");
        }
//        else if (isEmailInvalid) {
//            return new ResponseMessage<>(403, "Error: Email is invalid", "");
//        }
        else if (isPhoneInvalid) {
            return new ResponseMessage<>(404, "Error: Hãy nhập số điện thoại ít nhất 10 ký tự", "");
        }
        else if (isPasswordInvalid) {
            return new ResponseMessage<>(405, "Error: Hãy nhập mật khẩu trong khoảng 8-20 ký tự", "");
        } else {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            user.setCreatedDate(date.format(formatter));
            userRepository.save(user);
            return new ResponseMessage<>(200, "Success - Đăng ký tài khoản thành công", "" /*Access token*/);
        }
    }
}
