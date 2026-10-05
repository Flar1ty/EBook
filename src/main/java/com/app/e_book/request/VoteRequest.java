package com.app.e_book.request;

import com.app.e_book.entities.Post;
import com.app.e_book.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequest {
    private boolean upVote;
    @NotNull
    private Post post;
}
