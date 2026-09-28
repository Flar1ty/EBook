package com.app.e_book.web;

import com.app.e_book.entities.User;
import com.app.e_book.database.UserRepository;
import com.app.e_book.request.RegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/register")
//@SessionAttributes("registerRequest")
public class RegisterController {
    static String salt = BCrypt.gensalt();
    public RegisterController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;
    /*@ModelAttribute(name = "registerRequest")
    public RegisterRequest registerRequest(){
        return new RegisterRequest();
    }*/
    @GetMapping
    public String getPage(Model model){
        model.addAttribute("registerRequest", new RegisterRequest());
        return "registerPage";
    }
    @PostMapping
    public String processRegistration(@Valid RegisterRequest registerRequest, Errors errors){
        if(userRepository.findByUsername(registerRequest.getUsername()).isEmpty()) {
            if (errors.hasErrors()) {
                return "registerPage";
            }
            if (registerRequest.getPassword().equals(registerRequest.getRepeatPassword())) {
                User user = new User();
                String hashedPassword = BCrypt.hashpw(registerRequest.getPassword(), salt);
                user.setUsername(registerRequest.getUsername());
                user.setPassword(hashedPassword);
                userRepository.save(user);
                log.info("User: " + registerRequest.getUsername() + " saved. {}", registerRequest);
                log.info("Password: " + registerRequest.getPassword() + " hashed to: " + hashedPassword);
                return "redirect:/";
            }
            return "registerPage";
        }
        else {
            return "registerPage";
        }
    }
}
