package com.oquintero.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

//The @Data annotation generates getter/setters for all fields, it will generate
//the toString(), hashcode(), equals() methods automatically for all non-transient fields
@Data
public class PostDto {
    private long id;

    //title should not be null or empty
    //title should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post title should have at least 10 characters")
    private String title;

    //description should not be null or empty
    //description should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    //post content should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
