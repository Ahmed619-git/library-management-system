package com.example.librarymanagementsystem.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.librarymanagementsystem.model.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);
    Optional<Member> findByUsername(String username);
    List<Member> findByMembershipDate(LocalDate date);
}
