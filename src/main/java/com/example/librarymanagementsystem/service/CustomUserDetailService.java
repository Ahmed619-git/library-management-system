package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.config.SpringSecurityFilter;
import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.respository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service    
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    SpringSecurityFilter springSecurityFilter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }
}
