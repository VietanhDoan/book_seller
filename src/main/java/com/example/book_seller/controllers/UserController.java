package com.example.book_seller.controllers;

import com.example.book_seller.configs.JwtTokenUtil;
import com.example.book_seller.models.JwtRequest;
import com.example.book_seller.models.JwtResponse;
import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.User;
import com.example.book_seller.repositories.UserRepository;
import com.example.book_seller.services.JwtUserDetailsService;
import com.example.book_seller.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseMessage<JwtResponse>> createAuthenticationToken(@RequestBody User user) throws Exception {

        authenticate(user.getEmail(), user.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);


        return ResponseEntity.ok(new ResponseMessage<>(200, "Success - Đăng nhập thành công", new JwtResponse(token, userRepository.findByEmail(user.getEmail()).getId())));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(path = "/logout", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> logout(@RequestBody String string) {
        return ResponseEntity.ok(new ResponseMessage<>(200, "Success - Đăng xuất thành công", ""));
    }

    @GetMapping(path = "/profile", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<User>> getProfile(@RequestBody User user) {
        return ResponseEntity.ok(userService.getProfile(user));
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> updateAccount(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateAccount(user));
    }

    @DeleteMapping(path = "/delete", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> deleteAccount(@RequestBody User user) {
        return ResponseEntity.ok(userService.deleteAccount(user));
    }
}
