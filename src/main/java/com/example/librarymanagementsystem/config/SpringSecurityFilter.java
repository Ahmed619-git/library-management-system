package com.example.librarymanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityFilter {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // disable CSRF for APIS
                .authorizeHttpRequests(auth -> auth
                        // Customize Request for Member
                        .requestMatchers("/member/signup").permitAll()
                        .requestMatchers("/member").hasAnyRole("LIBRARIAN", "ADMIN")
                        .requestMatchers("/member/get/id/*", "/member/update/id/*").hasAnyRole("LIBRARIAN", "MEMBER")
                        .requestMatchers("/member/delete/id/*", "/member/membership_date/*").hasRole("LIBRARIAN")

                        // Customize Request for Book
                        .requestMatchers("/book", "/book/title/*").permitAll()    // public request
                        .requestMatchers("/book/add", "/book/update/title/*", "/book/delete/title/*").hasAnyRole("LIBRARIAN", "ADMIN")

                        // Customize Request for Borrow Record
                        .requestMatchers("/borrow_record").hasAnyRole("LIBRARIAN", "ADMIN")
                        .requestMatchers("/borrow_record/issue_book", "/borrow_record/return_book").hasAnyRole("LIBRARIAN", "MEMBER")
                        .requestMatchers("/borrow_record/issue_date/*").hasRole("LIBRARIAN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll())
                .httpBasic(basic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
