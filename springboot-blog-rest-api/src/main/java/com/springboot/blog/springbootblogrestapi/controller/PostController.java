package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postservice;

@PostMapping
 public ResponseEntity<PostDto>createPost(@RequestBody PostDto postdto){
        return new ResponseEntity<>(postservice.createPost(postdto), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDto> getPost(){
        return postservice.getPost();
    }
}
