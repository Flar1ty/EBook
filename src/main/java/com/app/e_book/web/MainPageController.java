package com.app.e_book.web;

import com.app.e_book.database.PostRepository;
import com.app.e_book.database.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public MainPageController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetails user){
        if(user != null && user.isEnabled()){
            model.addAttribute("userId", userRepository.findByUsername(user.getUsername()).get().getId());
            model.addAttribute("carma", userRepository.findByUsername(user.getUsername()).get().getRating());
        }
        model.addAttribute("posts", postRepository.findAll());
        return "mainPage";
    }
}
