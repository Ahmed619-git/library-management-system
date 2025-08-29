package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.respository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberService.class);
    MemberRepository memberRepository;

    //Constructor Injection
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean registerMember(Member member) {

        if(!memberRepository.existsByUsername(member.getUsername())) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));   // Encode Password
            memberRepository.save(member);
            log.info("member with username: {} has successfully added.", member.getUsername());
            return true;
        }
        else {
            log.warn("member with username: {} have already exist.", member.getUsername());
            return false;
        }
    }

    public Member update(Long id, Member new_member) {

        Member old_member = memberRepository.findById(id).orElse(null);

        if(old_member != null) {
            old_member.setName(new_member.getName());
            old_member.setUsername(new_member.getUsername());
            old_member.setPassword(passwordEncoder.encode(new_member.getPassword()));
            old_member.setRole(new_member.getRole());
            old_member.setEmail(new_member.getEmail());
            old_member.setMembershipDate(new_member.getMembershipDate());
            memberRepository.save(old_member);
            log.info("member with id: {} has been updated.", old_member.getId());
            return old_member;
        }
        else {
            log.warn("member with id: {} does not exist.", new_member.getId());
            return null;
        }
    }

    public boolean delete(Long id) {
        if(memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            log.info("member with id: {} has been deleted.", id);
            return true;
        }
        else {
            log.warn("member with id: {} does not exists.", id);
            return false;
        }
    }

    public Member getById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member != null) {
            log.info("member with id: {} has been provided.", id);
            return member;
        } else {
            log.warn("member with id: {} doesn't exist.", id);
            return null;
        }
    }

    public List<Member> getAll() {
        return new ArrayList<>(memberRepository.findAll());
    }

    public List<Member> getByMemberShipDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return memberRepository.findByMembershipDate(localDate);
    }
}
