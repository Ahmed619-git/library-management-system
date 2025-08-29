package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.respository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    Logger log = LoggerFactory.getLogger(BookService.class);

    BookRepository bookRepository;

    //Constructor Injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean add(Book b) {
        if(!bookRepository.existsByTitle(b.getTitle())) {
            bookRepository.save(b);
            log.info("new Book has been recorded.");
            return true;
        }
        else {
            log.warn("given book is already exists.");
            return false;
        }
    }

    public Book update(String title, Book new_book) {

        Book old_book = bookRepository.findByTitle(title).orElse(null);

        if(old_book != null) {
            old_book.setAuthor(new_book.getAuthor());
            old_book.setIsbn(new_book.getIsbn());
            old_book.setAvailableCopies(new_book.getAvailableCopies());
            log.info("book with title: {} has been updated.", title);
            bookRepository.save(old_book);
            return old_book;
        }
        else {
            log.warn("book with title: {} does not exist.", title);
            return null;
        }
    }

    public boolean delete(String title) {

        Book book = bookRepository.findByTitle(title).orElse(null);

        if(book != null) {
            bookRepository.delete(book);
            log.info("book with title: {} has been deleted.", title);
            return true;
        }
        else {
            log.warn("book with title: {} does not exist. ", title);
            return false;
        }
    }

    public Book getByTitle(String title) {

        Book book = bookRepository.findByTitle(title).orElse(null);

        if(book != null){
            log.info("book with title: {} has been provided.", title);
            return book;
        }
        else {
            log.warn("book with title: {} does not exists.", title);
            return null;
        }
    }

    public List<Book> getBookList() {
        return new ArrayList<>(bookRepository.findAll());
    }
}
