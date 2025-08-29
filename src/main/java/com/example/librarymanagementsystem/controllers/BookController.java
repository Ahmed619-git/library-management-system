package com.example.librarymanagementsystem.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.model.Book;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    BookService bookService;

    //Constructor Injection
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(bookService.getBookList() ,HttpStatus.OK);
    }

    @GetMapping("title/{title}")
    public ResponseEntity<Book> getByTitle(@PathVariable String title) {
        Book book = bookService.getByTitle(title);
        if(book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Book> add(@RequestBody @Valid Book book) {
        if(bookService.add(book)) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/title/{title}")
    public ResponseEntity<Book> update(@PathVariable String title, @RequestBody @Valid Book book) {
        Book updated_book = bookService.update(title, book);
        if(updated_book != null) {
           return new ResponseEntity<>(updated_book, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/title/{title}")
    public ResponseEntity<Void> delete(@PathVariable String title) {
        if(bookService.delete(title)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
