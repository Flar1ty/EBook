package com.app.e_book.request;

import com.app.e_book.entities.Post;
import com.app.e_book.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class CommentaryRequest {
    @NotBlank
    private String commentaryText;
    private User user;
    private UUID postId;
}
