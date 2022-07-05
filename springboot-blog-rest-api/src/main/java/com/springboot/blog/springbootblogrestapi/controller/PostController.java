package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.dto.PostResponse;
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
    public PostResponse getPost(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
                                @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy ){
        return postservice.getPost(pageNo,pageSize,sortBy);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable(name = "id") Long id){
        return  ResponseEntity.ok(postservice.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto>updatePostById(@RequestBody PostDto postdto ,@PathVariable(name = "id") Long id){
    return new ResponseEntity<>(postservice.updatePost(postdto,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePostById(@PathVariable(name = "id") Long id){
            postservice.deletePostById(id);
            return new ResponseEntity<>("Post entity of id:"+id+" is deleted successfully",HttpStatus.OK);
    }

}
