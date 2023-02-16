package com.springboot.blog.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    // mappedBy: let Hibernate to use one to many bidirectional mapping
        // and do not create additional join tables
    // cascade: ALL means all operations effect on both parent and child entity
    // orphanRemoval: let Hibernate remove all orphaned entities from database table,
        // which means child entities do not have parent reference,
        // Hibernate will remove child entities from database table.
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;
}
