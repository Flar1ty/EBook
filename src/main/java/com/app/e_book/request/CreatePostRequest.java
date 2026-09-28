package com.app.e_book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CreatePostRequest {
    @NotBlank
    private String postName;
    @NotBlank
    private String postText;
    private Set<String> postTags;
}
