package com.app.e_book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    @Size(min = 3, message = "Name should be at least size 3")
    private String username;
    @NotBlank
    @Size(min = 3, message = "Password should be at least size 3")
    private String password;
}
