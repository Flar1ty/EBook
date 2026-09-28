package com.app.e_book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, message = "Name should be at least size 3")
    private String username;
    @NotBlank
    @Size(min = 3, message = "Password should be at least size 3")
    private String password;
    private String repeatPassword;
}
