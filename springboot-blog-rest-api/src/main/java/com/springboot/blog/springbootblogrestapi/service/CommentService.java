package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.dto.Commentdto;
import com.springboot.blog.springbootblogrestapi.dto.PostDto;

import java.util.List;

public interface CommentService {

    public Commentdto createComment(long postId,Commentdto commentdto);
    public List<Commentdto> getComments(long postId);
    public Commentdto getCommentById( long postId,long id);
    public Commentdto updateComment(long id,Commentdto commentdto);
    public void deleteCommentById(long id);

}
