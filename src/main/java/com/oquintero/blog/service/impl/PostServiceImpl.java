package com.oquintero.blog.service.impl;

import com.oquintero.blog.entity.Post;
import com.oquintero.blog.exception.ResourceNotFoundException;
import com.oquintero.blog.payload.PostDto;
import com.oquintero.blog.payload.PostResponse;
import com.oquintero.blog.repository.PostRepository;
import com.oquintero.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //Mapping a PostDTO to Post entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        //Mapping entity to DTO
        PostDto postResponse = mapToDTO(newPost);

        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        //create sort object with direction
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();

        //create a Pageable instance for pagination
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pageOfPosts = postRepository.findAll(pageable);

        //get content for page object
        List<Post> posts = pageOfPosts.getContent();

        List<PostDto> content = posts.stream().map( post -> mapToDTO(post)).collect(Collectors.toList());

        //create response object with all details of pagination
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(pageOfPosts.getNumber());
        postResponse.setPageSize(pageOfPosts.getSize());
        postResponse.setTotalElements(pageOfPosts.getTotalElements());
        postResponse.setTotalPages(pageOfPosts.getTotalPages());
        postResponse.setLast(pageOfPosts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostByID(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Post", "post", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        //get post by id from database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "post", id));

        //update values into post object
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post updated = postRepository.save(post);

        return mapToDTO(updated);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","post", id));
        postRepository.delete(post);
    }

    //Map a Post object into DTO object
    private PostDto mapToDTO(Post post){
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    //Map a PostDTO object into Entity object
    private Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }
}
