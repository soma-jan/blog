package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.dto.Commentdto;
import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentservice;

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<Commentdto> createcomment(@PathVariable(value="postId") long postId ,
                                                    @RequestBody Commentdto commentdto){
        return new ResponseEntity<>(commentservice.createComment(postId,commentdto), HttpStatus.CREATED);
    }

    @GetMapping("posts/{postId}/comments")
    public List<Commentdto> getComments(@PathVariable(value = "postId") long postId){
    return  commentservice.getComments(postId);
    }

    @GetMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<Commentdto> getComments(@PathVariable(value = "postId") long postId,
                                        @PathVariable(value = "id") long id){
        return new ResponseEntity<>(commentservice.getCommentById(postId,id),HttpStatus.OK) ;
    }

    @PutMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<Commentdto>updateCommentById(@RequestBody Commentdto commentdto ,@PathVariable(name = "id") long id){
        return new ResponseEntity<>(commentservice.updateComment(id,commentdto),HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<String>deleteCommenttById(@PathVariable(name = "id") long id){
    commentservice.deleteCommentById(id);
    return new ResponseEntity<>("Comment entity of id:"+id+" is deleted successfully",HttpStatus.OK);
    }
}
