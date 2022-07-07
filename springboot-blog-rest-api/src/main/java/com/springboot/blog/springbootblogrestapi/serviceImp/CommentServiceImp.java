package com.springboot.blog.springbootblogrestapi.serviceImp;

import com.springboot.blog.springbootblogrestapi.dao.CommentRepository;
import com.springboot.blog.springbootblogrestapi.dao.PostRepository;
import com.springboot.blog.springbootblogrestapi.dto.Commentdto;
import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.exception.BlogAPIException;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFound;
import com.springboot.blog.springbootblogrestapi.model.Comment;
import com.springboot.blog.springbootblogrestapi.model.Post;
import com.springboot.blog.springbootblogrestapi.service.CommentService;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {


    @Autowired
    private CommentRepository commentrepository;
    @Autowired
    private PostRepository postrepository;

    @Override
    public Commentdto createComment(long postId, Commentdto commentdto) {
        Comment comment =mapToEntity(commentdto);
        Post post = postrepository.findById(postId).orElseThrow(()->new ResourceNotFound("Post","id",postId));
        comment.setPost(post);
        Comment newComment = commentrepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<Commentdto> getComments(long postId) {
        List<Comment> comments= commentrepository.findByPostId(postId) ;
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public Commentdto getCommentById(long postId, long id) {
        Post post = postrepository.findById(postId).orElseThrow(()->new ResourceNotFound("Post","id",postId));
        Comment comment= commentrepository.findById(id).orElseThrow(()->new ResourceNotFound("Comments","id",id));
        if(!comment.getPost().getId().equals(postId)){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong this post");
        }
        return mapToDto(comment);
    }

    @Override
    public Commentdto updateComment(long id, Commentdto commentdto) {
        Comment comment= commentrepository.findById(id).orElseThrow(()->new ResourceNotFound("Comments","id",id));
        comment.setName(commentdto.getName());
        comment.setBody(commentdto.getBody());
        comment.setEmail(commentdto.getEmail());
        Comment  newcomment =commentrepository.save(comment);
        return mapToDto(newcomment);
    }

    @Override
    public void deleteCommentById(long id) {
        Comment comment= commentrepository.findById(id).orElseThrow(()->new ResourceNotFound("Comments","id",id));
        commentrepository.delete(comment);

    }


    public Comment mapToEntity(Commentdto commentdto){
        Comment comment = new Comment();
        comment.setId(commentdto.getId());
        comment.setName(commentdto.getName());
        comment.setEmail(commentdto.getEmail());
        comment.setBody(commentdto.getBody());
        return comment;
    }
    public Commentdto mapToDto(Comment comment){
        Commentdto commentdto = new Commentdto();
        commentdto.setId(comment.getId());
        commentdto.setName(comment.getName());
        commentdto.setEmail(comment.getEmail());
        commentdto.setBody(comment.getBody());
        return commentdto;
    }
}
