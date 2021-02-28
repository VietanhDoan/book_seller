package com.example.book_seller.services;

import com.example.book_seller.models.ResponseMessage;
import com.example.book_seller.models.entities.BookEntity;
import com.example.book_seller.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    public List<BookEntity> getBookList(String accessToken, String groupBy, String genreID, String sortedBy, Integer offset, Integer limit) {
    public List<BookEntity> getBookList() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookEntities;
    }

    public ResponseMessage<String> addBook(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
        return new ResponseMessage<>(200, "Success", "");
    }

    public ResponseMessage<String> updateBook(BookEntity bookEntity) {
        if (!bookRepository.existsById(bookEntity.getId())) {
            return new ResponseMessage<>(401, "Error: Không tìm thấy cuốn sách này", "");
        } else {
            bookRepository.save(bookEntity);
            return new ResponseMessage<>(200, "Success", "");
        }
    }

    public ResponseMessage<String> deleteBook(BookEntity bookEntity) {
        if (!bookRepository.existsById(bookEntity.getId())) {
            return new ResponseMessage<>(401, "Error: Không tìm thấy cuốn sách này", "");
        } else {
            bookRepository.deleteById(bookEntity.getId());
            return new ResponseMessage<>(200, "Success", "");
        }
    }

    public ResponseMessage<BookEntity> getBookDetail(String id) {
        if (!bookRepository.existsById(Integer.valueOf(id))) {
            return new ResponseMessage<>(401, "Error: Không tìm thấy cuốn sách này", null);
        } else {
            BookEntity bookEntity = bookRepository.findBookById(Integer.valueOf(id));
            return new ResponseMessage<>(200, "Success", bookEntity);
        }
    }
}
