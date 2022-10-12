package com.oquintero.blog.service;

import com.oquintero.blog.payload.PostDto;
import com.oquintero.blog.payload.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostByID(long id);

    PostDto updatePost(long id, PostDto postDto);

    void deletePost(long id);
}
