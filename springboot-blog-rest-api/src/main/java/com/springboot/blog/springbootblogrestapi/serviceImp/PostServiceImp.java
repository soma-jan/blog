package com.springboot.blog.springbootblogrestapi.serviceImp;

import com.springboot.blog.springbootblogrestapi.dao.PostRepository;
import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFound;
import com.springboot.blog.springbootblogrestapi.model.Post;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository postrepository;
    @Override
    public PostDto createPost(PostDto postdto) {
        Post post = mapToEntity(postdto);
        Post newpost = postrepository.save(post);
        return mapToDto(newpost);

    }
    @Override
    public List<PostDto> getPost() {
     List<Post> posts =postrepository.findAll();
     return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postrepository.findById(id).orElseThrow(()-> new ResourceNotFound("post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postdto, Long id) {
        Post post = postrepository.findById(id).orElseThrow(()-> new ResourceNotFound("post","id",id));
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        Post updatedpost= postrepository.save(post);
        return mapToDto(updatedpost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postrepository.findById(id).orElseThrow(()-> new ResourceNotFound("post","id",id));
         postrepository.delete(post);

    }

    public Post mapToEntity(PostDto postdto){

        Post post = new Post();
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        return post;
    }
    public PostDto mapToDto(Post post){
        PostDto postResponse =new PostDto();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setContent(post.getContent());
        return  postResponse;
    }
}
