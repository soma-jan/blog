package com.springboot.blog.springbootblogrestapi.dao;


import com.springboot.blog.springbootblogrestapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
