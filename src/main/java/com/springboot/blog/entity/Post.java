package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

// JPA entity creation for Post
// Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// Entity represents each table
@Entity
@Table(
        // table name
        name = "posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {
    // Id: Primary key of entity
    // GeneratedValue: Primary key generation strategy
        // AUTO: Primary key is controlled by the program and is default option.
        // IDENTITY: Primary key is automatically generated by the database,
                    // i.e., the database ID is used to self-grow
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    // Column name of field title
    // Note: JPA automatically build the table field naming rules using the underscore,
    // such as scenicName will generate the database field scenic_name,
    // if you need to generate non-underscore fields, you can use @Column(name = "ScenicName")
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    // whenever loading Post JPA entity, the category object will not load immediately
    // get Category object from Post entity object on demand by calling the getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
