package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springboot.blog.utils.AppConstants.*;

// Versioning REST API through URI path
// @RequestMapping("/api/v1/posts")
// @RequestMapping("/api/v2/posts")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    // DTO interface
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create Category post api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts rest api
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUM, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sorBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }


    // Versioning REST API through query parameters
    // @GetMapping("/{id}", params = "version=1")
    // @GetMapping("/{id}", params = "version=2")

    // Versioning REST API through custom headers
    // E.g. Postman -> Headers -> X-API-VERSION : 1
    // @GetMapping("/{id}", headers = "X-API-VERSION=1")
    // @GetMapping("/{id}", headers = "X-API-VERSION=2")

    // Versioning REST API through content negotiation
    // E.g. Postman -> Headers -> Accept : application/vnd.java.v1+json
    // @GetMapping("/{id}", produces = "application/vnd.java.v1+json")
    // @GetMapping("/{id}", produces = "application/vnd.java.v2+json")

    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update post by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable(name="id") long id) {
        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }

    // delete post by id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post entity deleted successfully!");
    }

    // Get post by category API
    // http://localhost:8080/api/posts/category/{id}
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping ("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name="id") Long categoryId) {

        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}
