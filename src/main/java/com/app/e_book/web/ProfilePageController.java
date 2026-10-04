package com.app.e_book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/{id}/profile")
public class ProfilePageController {
    @GetMapping
    public String getPage(@PathVariable("id") String id){





        return "profilePage";
    }
}
