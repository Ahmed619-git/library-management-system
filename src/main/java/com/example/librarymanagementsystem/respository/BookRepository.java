package com.example.librarymanagementsystem.respository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitle(String title);

    Optional<Book> findByTitle(String title);
}