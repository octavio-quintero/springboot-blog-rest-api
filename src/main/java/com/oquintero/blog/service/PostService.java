package com.oquintero.blog.service;

import com.oquintero.blog.payload.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostByID(long id);
}
