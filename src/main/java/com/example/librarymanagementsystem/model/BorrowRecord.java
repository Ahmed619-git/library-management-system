package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Entity
public class BorrowRecord {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "book name cannot be blank")
    private String book;

    @NotBlank(message = "member name cannot be blank")
    private String member;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    @PositiveOrZero(message = "bookCopies cannot be negative")
    private Integer bookCopies;

    //Constructors
    public BorrowRecord() {
    }

    public BorrowRecord(Long id, String book, String member, LocalDate borrowDate, LocalDate returnDate, Integer bookCopies) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.bookCopies = bookCopies;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public String getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Integer getBookCopies() {
        return bookCopies;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setBookCopies(Integer bookCopies) {
        this.bookCopies = bookCopies;
    }
}
