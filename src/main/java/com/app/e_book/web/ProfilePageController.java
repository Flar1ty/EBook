package com.app.e_book.web;

import com.app.e_book.database.UserRepository;
import com.app.e_book.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/user/{id}/profile")
public class ProfilePageController {
    private UserRepository userRepository;

    public ProfilePageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getPage(@PathVariable("id") UUID id, Model model){
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("username", user.getUsername());
        model.addAttribute("description", user.getDescription());
        return "profilePage";
    }
}
