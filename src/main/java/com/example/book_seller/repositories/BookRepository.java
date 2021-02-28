package com.example.book_seller.repositories;

import com.example.book_seller.models.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findAllByGenre(String genre);

    BookEntity findBookById(Integer id);


}
