package com.oquintero.blog.repository;

import com.oquintero.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //Query method created to get all comments from a postId.
    List<Comment> findByPostId(long postId);


}
