package com.example.book_seller.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "book")
@Getter
@Setter
public class BookEntity {
    @Column(name = "id")
    Integer id;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    @Column(name = "image")
    String image;
    @Column(name = "author")
    String author;
    @Column(name = "genre")
    String genre;
    @Column(name = "favourite")
    Integer favourite;
    @Column(name = "price")
    Integer price;
    @Column(name = "release_date")
    String releaseDate;

    public BookEntity(Integer id, String title, String description, String image, String author, String genre, Integer favourite, Integer price, String releaseDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
        this.genre = genre;
        this.favourite = favourite;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public BookEntity() {
    }
}
