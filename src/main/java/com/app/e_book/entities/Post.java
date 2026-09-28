package com.app.e_book.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", postName='" + postName + '\'' +
                ", postTags=" + postTags +
                ", postText='" + postText + '\'' +
                ", commentaries=" + commentaries +
                ", date=" + date +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String postName;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> postTags;

    @Column(columnDefinition = "TEXT")
    private String postText;

    @OneToMany(mappedBy = "post")
    private List<Commentary> commentaries;

    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date;
}
