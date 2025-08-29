package com.example.librarymanagementsystem.respository;

import com.example.librarymanagementsystem.model.BorrowRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByBorrowDate(LocalDate date);

    boolean existsByBookAndMember(String book, String member);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.availableCopies = b.availableCopies + 1 WHERE b.title = :title")
    void increaseBookCopiesByBookTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.availableCopies = b.availableCopies - 1 WHERE b.title = :title")
    void decreaseBookCopiesByBookTitle(@Param("title") String title);

    @Transactional
    void deleteByBookAndMember(String bookTitle, String member);
}