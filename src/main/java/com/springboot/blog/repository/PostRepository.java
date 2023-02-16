package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    // The JpaRepository interface has both basic CRUD functionality and paging functionality

    // Difference between JPA and Mybatis framework
    // JPA framework has good portability, do not care what database to use.
    // Mybatis framework requires changes to the underlying SQL when changing the database.

    List<Post> findByCategoryId(Long categoryId);
}
