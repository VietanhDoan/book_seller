package com.example.book_seller.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String image;
    private String author;
    private String genre;
    private Integer favourite;
    private Integer price;
    private String releaseDate;

    public Book(Integer id, String title, String description, String image, String author, String genre, Integer favourite, Integer price, String releaseDate) {
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

    public Book() {
    }
}
