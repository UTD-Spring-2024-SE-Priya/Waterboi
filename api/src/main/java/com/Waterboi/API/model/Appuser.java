package com.waterboi.api.model;

import jakarta.persistence.*;

@Entity
public class Appuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    Appuser() {}

    public Appuser(String username, String password) {
        this.username = username.toLowerCase();
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {return id;}
}
