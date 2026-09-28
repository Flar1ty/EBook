package com.app.e_book.web;

import com.app.e_book.request.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @ModelAttribute("loginRequest")
    public LoginRequest loginRequest(){
        return new LoginRequest();
    }
    @GetMapping
    public String getPage(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "loginPage";
    }
}
