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

    public ResponseMessage<String> logout() throws Exception {
        try {
            return new ResponseMessage<>(200, "Success - Đăng xuất tài khoản thành công", "");
        } catch (Exception exception) {
            System.out.println("ERROR");
        }
        return new ResponseMessage<>(200, "Success - Đăng xuất tài khoản thành công", "");
    }

    public ResponseMessage<User> getProfile(User user) {
        boolean isExisted = userRepository.existsById(user.getId());
        if (isExisted) {
            User user1 = userRepository.findById(user.getId()).get();
            return new ResponseMessage<>(200, "Success - Xem thông tin tài khoản", user1);
        }
        return new ResponseMessage<>(401, "Error - Tài khoản không tồn tại", null);
    }

    public ResponseMessage<String> updateAccount(User user) {
        boolean isExisted = userRepository.existsById(user.getId());
        if (isExisted) {
            User user1 = userRepository.findById(user.getId()).get();
            user1.setUsername(user.getUsername());
//            user1.setEmail(user.getEmail());
//            user1.setPassword(user.getPassword());
            user1.setPhone(user.getPhone());
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            user1.setCreatedDate(date.format(formatter));
            userRepository.save(user1);
            return new ResponseMessage<>(200, "Success - Sửa thông tin tài khoản thành công", "");
        }
        return new ResponseMessage<>(401, "Error - Tài khoản không tồn tại", null);
    }

    public ResponseMessage<String> deleteAccount(User user) {
        boolean isExisted = userRepository.existsById(user.getId());
        if (isExisted) {
            userRepository.deleteById(user.getId());
            return new ResponseMessage<>(200, "Success - Xóa thông tin tài khoản thành công", "");
        }
        return null;
    }
}
