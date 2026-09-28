package com.app.e_book.web;

import com.app.e_book.database.PostRepository;
import com.app.e_book.database.TagRepository;
import com.app.e_book.database.UserRepository;
import com.app.e_book.entities.Post;
import com.app.e_book.entities.Tag;
import com.app.e_book.entities.User;
import com.app.e_book.request.CreatePostRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/create")
public class PostCreationController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    public PostCreationController(PostRepository postRepository, UserRepository userRepository, TagRepository tagRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }
    @GetMapping
    public String getPage(Model model){
        model.addAttribute("createPostRequest", new CreatePostRequest());
        return "createPost";
    }
    @PostMapping
    public String getPost(@Valid CreatePostRequest createPostRequest, Errors errors, @AuthenticationPrincipal UserDetails user){
        if(errors.hasErrors()) {
            return "createPost";
        }
        Post post = new Post();
        post.setPostName(createPostRequest.getPostName());
        post.setPostText(createPostRequest.getPostText());
        Set<Tag> tags = new HashSet<>();
        for(String tag : createPostRequest.getPostTags()){
            Tag tag1 = new Tag();
            tag1.setName(tag);
            tag1.setHex("#000000");
            tag1.setBlack(false);
            tags.add(tag1);
            tagRepository.save(tag1);
        }
        post.setPostTags(tags);
        post.setUser(userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Fucking nigger exception")));
        postRepository.save(post);
        log.info("New post was added {}", post);
        System.out.println(postRepository.findByPostName(post.getPostName()).get().getDate());
        return "redirect:/";
    }
}
