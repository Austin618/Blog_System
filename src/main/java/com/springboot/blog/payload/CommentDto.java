package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    // title should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    // email should not be null or empty
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    // description should not be null or empty
    // description should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Comment body should have at least 10 characters")
    private String body;
}


