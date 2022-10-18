package com.oquintero.blog.payload;

import lombok.Data;

import java.util.Set;

//The @Data annotation generates getter/setters for all fields, it will generate
//the toString(), hashcode(), equals() methods automatically for all non-transient fields
@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
