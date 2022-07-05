package com.springboot.blog.springbootblogrestapi.serviceImp;

import com.springboot.blog.springbootblogrestapi.dao.PostRepository;
import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.dto.PostResponse;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFound;
import com.springboot.blog.springbootblogrestapi.model.Post;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getPost(int pageNo, int pageSize,String sortBy) {

     Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
     Page<Post> posts =postrepository.findAll(pageable);
     List<Post> postlist=posts.getContent();
     List<PostDto> content = postlist.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
     PostResponse postresponse =new PostResponse();
     postresponse.setContent(content);
     postresponse.setPageNo(posts.getNumber());
     postresponse.setPageSize(posts.getSize());
     postresponse.setTotalElements(posts.getTotalElements());
     postresponse.setLast(posts.isLast());
     return postresponse;
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
