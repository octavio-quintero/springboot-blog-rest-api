package com.oquintero.blog.controller;

import com.oquintero.blog.payload.PostDto;
import com.oquintero.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //create a blog post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post){
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    //get all posts rest api
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
        return ResponseEntity.ok(postService.getPostByID(id));
    }
}
