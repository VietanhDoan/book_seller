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
@RequestMapping(path = "/api/books")
public class BookController {

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ResponseMessage<List<Book>>> getBookList(
//            @RequestParam(name = "accessToken") String accessToken,
            @RequestParam(name = "groupBy", required = false) String groupBy,
            @RequestParam(name = "genreID", required = false) String genreID,
            @RequestParam(name = "sortedBy", required = false) String sortedBy,
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "limit", required = false) Integer limit
    ) {
//        List<Book> books = bookService.getBookList(accessToken, groupBy, genreID, sortedBy, offset, limit);
        List<Book> books = bookService.getBookList();
        return ResponseEntity.ok(new ResponseMessage<>(200, "", books));
    }

    @PostMapping(path = "/post/add", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> postBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }
}
