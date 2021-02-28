package com.example.book_seller.services;

import java.util.ArrayList;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.UserEntity;
import com.example.book_seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity != null) {
            return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), bcryptEncoder.encode(userEntity.getPassword()),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Error: Tài khoản chưa được đăng ký" + username);
        }
    }

    public ResponseMessage<String> login(UserEntity userEntity) {
        String email = userEntity.getEmail();
        String password = userEntity.getPassword();
        boolean isPasswordInvalid = password.length() < 8 || password.length() > 20;
        if (isPasswordInvalid) {
            return new ResponseMessage<>(401, "Error: Hãy nhập mật khẩu trong khoảng 8-20 ký tự", "");
        }
        if (userRepository.existsByEmail(email) && userRepository.existsByPassword(password)) {
            return new ResponseMessage<>(200, "Success - Đăng nhập thành công", ""/*Access token*/);
        }
        return new ResponseMessage<>(402, "Error: Tài khoản chưa được đăng ký", "");
    }
}