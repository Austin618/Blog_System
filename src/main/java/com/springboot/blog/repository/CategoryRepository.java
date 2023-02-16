package com.springboot.blog.repository;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository<Category, Long>: JpaRepository<entityName, primaryKeyType>
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
