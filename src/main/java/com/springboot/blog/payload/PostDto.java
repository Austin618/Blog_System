package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

// DTO structure advantages:
// Return DTO to the client will help to hide implementation details of JPA entities,
// and exposing JPA entities through REST endpoints may cause a security issue


// Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@Data
public class PostDto {
    private long id;

    // title should not be null or empty
    // title should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // description should not be null or empty
    // description should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    // description should not be null or empty
    // description should have at least 10 characters
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    private Long categoryId;
}


