package com.example.lib_mgmt.sys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable( name = "books_authors",
                joinColumns = {@JoinColumn(name = "book_id")},
                inverseJoinColumns = {@JoinColumn(name = "author_id")}
                )
    private List<Author> authors;

    @ManyToMany
    @JoinTable( name = "books_categories",
                    joinColumns = {@JoinColumn(name = "book_id")},
                    inverseJoinColumns = {@JoinColumn(name = "category_id")}
                )
    private List<Category> categories;

    @ManyToMany
    @JoinTable( name = "books_publishers",
                    joinColumns = {@JoinColumn(name = "book_id")},
                    inverseJoinColumns = {@JoinColumn(name = "publisher_id")}
                )
    private List<Publisher> publishers;
}
