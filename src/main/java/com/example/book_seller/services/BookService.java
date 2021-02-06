package com.example.book_seller.services;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.Book;
import com.example.book_seller.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    public List<Book> getBookList() {
//        bookRepository;
//        return new ResponseMessage<>(200, "Register success", "");
//    }

    public ResponseMessage<String> addBook(Book book) {
        bookRepository.save(book);
        return new ResponseMessage<>(200, "Success", "");
    }
}
