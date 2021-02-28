package com.example.book_seller.repositories;

import com.example.book_seller.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
    Boolean existsByPassword(String password);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
