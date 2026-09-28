package com.app.e_book.web;

import com.app.e_book.database.CommentaryRepository;
import com.app.e_book.database.PostRepository;
import com.app.e_book.database.UserRepository;
import com.app.e_book.entities.Commentary;
import com.app.e_book.entities.Post;
import com.app.e_book.entities.User;
import com.app.e_book.exceptions.PostNotFoundException;
import com.app.e_book.request.CommentaryRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/post")
public class OpenPostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentaryRepository commentaryRepository;

    public OpenPostController(PostRepository postRepository, UserRepository userRepository, CommentaryRepository commentaryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentaryRepository = commentaryRepository;
    }

    @GetMapping("/{uuid}")
    public String getPage(@PathVariable("uuid") UUID uuid, Model model, @AuthenticationPrincipal UserDetails user){
        Post post = postRepository.findById(uuid).orElseThrow(() -> new PostNotFoundException("Post with UUID: " + uuid + " doesn't exist"));
        model.addAttribute("post", post);
        if(user != null && user.isEnabled()){
            model.addAttribute("commentaryRequest", new CommentaryRequest());
        }
        return "postPage";
    }
    @PostMapping("/{uuid}")
    public String handleCommentaryCreation(@Valid CommentaryRequest commentaryRequest, Errors errors,
                                                           @AuthenticationPrincipal UserDetails userDet, @PathVariable("uuid") UUID uuid){
        System.out.println("Processing post creation...");
        System.out.println("Current commentaries: " + commentaryRepository.findAll().size());
        if(errors.hasErrors()){
            return "redirect:/post/" + uuid;
        }
        else {
            User user = userRepository.findByUsername(userDet.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username wasn't found"));
            Commentary commentary = new Commentary();
            commentary.setPost(postRepository.findById(commentaryRequest.getPostId()).orElseThrow(() -> new PostNotFoundException("Post not found!")));
            commentary.setCommentaryText(commentaryRequest.getCommentaryText());
            commentary.setUser(user);
            log.info("New commentary was added!!!");
            commentaryRepository.save(commentary);
            return "redirect:/post/" + uuid;
        }
    }
}
