package com.example.book_seller.controllers;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.Book;
import com.example.book_seller.models.entities.User;
import com.example.book_seller.services.BookService;
import com.example.book_seller.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping
//    public ResponseEntity<ResponseMessage<List<Book>>> getBookList() {
//        List<Book> books = bookService
//        return ResponseEntity.ok(new ResponseMessage<>(200, "", books));
//    }

    @PostMapping(path = "/post/add", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> postBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }
}
