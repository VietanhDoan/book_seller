package com.example.book_seller.repositories;

import com.example.book_seller.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByGenre(String genre);

    Book findBookById(Integer id);


}
