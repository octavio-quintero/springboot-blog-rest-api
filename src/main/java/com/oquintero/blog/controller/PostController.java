package com.oquintero.blog.controller;

import com.oquintero.blog.payload.PostDto;
import com.oquintero.blog.payload.PostResponse;
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
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return postService.getAllPosts(pageNo, pageSize);
    }

    //get post by id rest api
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
        return ResponseEntity.ok(postService.getPostByID(id));
    }

    //update post rest api
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto){
        PostDto postResponse = postService.updatePost(id, postDto);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

}
