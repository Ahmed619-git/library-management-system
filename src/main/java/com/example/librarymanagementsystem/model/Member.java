package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "member name cannot be blank.")
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    private String role;

    @NotBlank(message = "email cannot be blank.")
    private String email;

//    @NotBlank(message = "membership date cannot be blank.")
    private LocalDate membershipDate;

    //Constructors
    public Member() {
    }

    public Member(Long id, String name, String username, String password, String role, String email, LocalDate membershipDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.membershipDate = membershipDate;
    }

    //Getter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }


    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }
}
