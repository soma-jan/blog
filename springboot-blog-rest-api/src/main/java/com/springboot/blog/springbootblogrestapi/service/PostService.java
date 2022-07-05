package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.dto.PostResponse;
import com.springboot.blog.springbootblogrestapi.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    public PostDto createPost(PostDto postdto);
    public PostResponse getPost(int pageNo, int pageSize,String sortBy);
    public PostDto getPostById(Long id);
    public PostDto updatePost(PostDto postdto,Long id);
    public void deletePostById(Long id);
}
