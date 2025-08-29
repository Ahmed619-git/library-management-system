package com.example.librarymanagementsystem.controllers;


import com.example.librarymanagementsystem.model.BorrowRecord;
import com.example.librarymanagementsystem.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow_record")
public class BorrowRecordController {

    @Autowired
    BorrowRecordService borrowRecordService;

    @PostMapping("/issue_book")
    public ResponseEntity<?> issueBook(@RequestBody BorrowRecord b) {
        boolean flag = borrowRecordService.issueBook(b);
        if(flag) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/return_book")
    public ResponseEntity<?> returnBook(@RequestBody BorrowRecord b) {
        boolean flag = borrowRecordService.returnBook(b);
        if(flag) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/issue_date/{date}")
    public ResponseEntity<List<BorrowRecord>> getByIssueDate(@PathVariable String date) {
        return new ResponseEntity<>(borrowRecordService.findByBorrowDate(date), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecord>> getAll() {
        return new ResponseEntity<>(borrowRecordService.findAll(), HttpStatus.OK);
    }
}
