package com.app.e_book.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    @OneToMany(mappedBy = "user")
    private List<Vote> votes;

    @OneToMany(mappedBy = "user")
    private List<Commentary> commentaries;
    @Column(name = "rating")
    private Long rating;
}
