package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowRecord;
import com.example.librarymanagementsystem.respository.BookRepository;
import com.example.librarymanagementsystem.respository.BorrowRecordRepository;
import com.example.librarymanagementsystem.respository.MemberRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.time.LocalDate;

@Service
public class BorrowRecordService {

    Logger log = LoggerFactory.getLogger(BorrowRecordService.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BorrowRecordRepository borrowRecordRepo;

    public boolean issueBook(BorrowRecord b) {

        if (memberRepository.existsByUsername(b.getMember())) {

            Book book = bookRepository.findByTitle(b.getBook()).orElse(null);

            if (bookRepository.existsByTitle(b.getBook()) && book.getAvailableCopies() > 0) {
                borrowRecordRepo.save(b);
                borrowRecordRepo.decreaseBookCopiesByBookTitle(b.getBook());
                log.info("book with title: {} has issued to member with name: {}!", b.getBook(), b.getMember());
                return true;
            } else {
                log.warn("book with title: {} is not available!", b.getBook());
                return false;
            }
        } else {
            log.warn("{} is not a registered member!", b.getMember());
            return false;
        }
    }

    @Transactional
    public boolean returnBook(BorrowRecord b) {

//        if(memberRepository.existsByUsername(b.getMember()) && bookRepository.existsByTitle(b.getBook()))

            boolean flag = borrowRecordRepo.existsByBookAndMember(b.getBook(), b.getMember());

            if(flag) {
            borrowRecordRepo.deleteByBookAndMember(b.getBook(), b.getMember());
            borrowRecordRepo.increaseBookCopiesByBookTitle(b.getBook());

            log.info("book with title: {} has returned by {}", b.getBook(), b.getMember());
            return true;
        }
        else {
            log.warn("member or book is not registered!");
            return false;
        }
    }

    public List<BorrowRecord> findByBorrowDate(String date) {

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new ArrayList<>(borrowRecordRepo.findByBorrowDate(localDate));
    }

    public List<BorrowRecord> findAll() {
        return borrowRecordRepo.findAll();
    }
}
