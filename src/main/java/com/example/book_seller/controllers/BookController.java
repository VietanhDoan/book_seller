package com.example.book_seller.controllers;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.Book;
import com.example.book_seller.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
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

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage<Book>> getBookDetail(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBookDetail(id));
    }
//
//    @PostMapping(path = "/logout", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> logout(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
//
//    @GetMapping(path = "/profile", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> getProfile(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
//
//    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> updateAccount(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }
//
//    @DeleteMapping(path = "/delete", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<ResponseMessage<String>> deleteAccount(@RequestBody User user) {
//        return ResponseEntity.ok(userService.register(user));
//    }

    @PostMapping(path = "/post/add", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> postBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping(path = "/post/update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> updateBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @DeleteMapping(path = "/post/delete", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> deleteBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.deleteBook(book));
    }
}
