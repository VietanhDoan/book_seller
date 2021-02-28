package com.example.book_seller.controllers;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.BookEntity;
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
    public ResponseEntity<ResponseMessage<List<BookEntity>>> getBookList(
//            @RequestParam(name = "accessToken") String accessToken,
            @RequestParam(name = "groupBy", required = false) String groupBy,
            @RequestParam(name = "genreID", required = false) String genreID,
            @RequestParam(name = "sortedBy", required = false) String sortedBy,
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "limit", required = false) Integer limit
    ) {
//        List<BookEntity> bookEntities = bookService.getBookList(accessToken, groupBy, genreID, sortedBy, offset, limit);
        List<BookEntity> bookEntities = bookService.getBookList();
        if (bookEntities.size() == 0) {
            return ResponseEntity.ok(new ResponseMessage<>(200, "Danh sách các sách bán trống", bookEntities));
        }
        return ResponseEntity.ok(new ResponseMessage<>(200, "", bookEntities));
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage<BookEntity>> getBookDetail(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBookDetail(id));
    }

    @PutMapping(path = "/{id}/favourite", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> favouriteBook(@PathVariable String id, @RequestBody BookEntity bookEntity) {
        return ResponseEntity.ok(bookService.updateBook(bookEntity));
    }

    @PostMapping(path = "/post/add", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> postBook(@RequestBody BookEntity bookEntity) {
        return ResponseEntity.ok(bookService.addBook(bookEntity));
    }

    @PutMapping(path = "/post/update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> updateBook(@RequestBody BookEntity bookEntity) {
        return ResponseEntity.ok(bookService.updateBook(bookEntity));
    }

    @DeleteMapping(path = "/post/delete", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ResponseMessage<String>> deleteBook(@RequestBody BookEntity bookEntity) {
        return ResponseEntity.ok(bookService.deleteBook(bookEntity));
    }
}
