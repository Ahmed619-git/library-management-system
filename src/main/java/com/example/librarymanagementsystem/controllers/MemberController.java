package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.service.MemberService;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    MemberService memberService;
    //Constructor Injection
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAll() {
        List<Member> list = memberService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Member> getById(@PathVariable Long id) {
        Member member = memberService.getById(id);
        if(member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/membership_date")
    public ResponseEntity<List<Member>> getByMembershipDate(@RequestParam(required = false) String date) {
        return new ResponseEntity<>(memberService.getByMemberShipDate(date), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody Member member) {
        if(memberService.registerMember(member)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody Member member) {
        Member old_member = memberService.update(id, member);
        if(old_member != null) {
            return new ResponseEntity<>(old_member, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(memberService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
