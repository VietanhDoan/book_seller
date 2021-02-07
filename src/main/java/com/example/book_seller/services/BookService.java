package com.example.book_seller.services;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.Book;
import com.example.book_seller.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    public List<Book> getBookList(String accessToken, String groupBy, String genreID, String sortedBy, Integer offset, Integer limit) {
    public List<Book> getBookList() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public ResponseMessage<String> addBook(Book book) {
        bookRepository.save(book);
        return new ResponseMessage<>(200, "Success", "");
    }

    public ResponseMessage<String> updateBook(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            return new ResponseMessage<>(401, "Error: This book is not found", "");
        } else {
            bookRepository.save(book);
            return new ResponseMessage<>(200, "Success", "");
        }
    }

    public ResponseMessage<String> deleteBook(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            return new ResponseMessage<>(401, "Error: This book is not found", "");
        } else {
            bookRepository.deleteById(book.getId());
            return new ResponseMessage<>(200, "Success", "");
        }
    }
}
