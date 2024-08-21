package com.example.lib_mgmt.sys.controller;

import com.example.lib_mgmt.sys.entity.Author;
import com.example.lib_mgmt.sys.entity.Book;
import com.example.lib_mgmt.sys.entity.Category;
import com.example.lib_mgmt.sys.entity.Publisher;
import com.example.lib_mgmt.sys.service.AuthorService;
import com.example.lib_mgmt.sys.service.BookService;
import com.example.lib_mgmt.sys.service.CategoryService;
import com.example.lib_mgmt.sys.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id){
        Book book = bookService.getBookById(id);
        if(book == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book){
        Book existingBook = bookService.getBookById(id);
        if(existingBook == null)
            ResponseEntity.notFound().build();
        book.setId(id);
        bookService.saveOrUpdateBook(book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id){
        Book existingBook = bookService.getBookById(id);
        if(existingBook == null)
            ResponseEntity.notFound().build();
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        List<Author> authors = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<Publisher> publishers = new ArrayList<>();

        //author
        for(Author author : book.getAuthors()){
            Author foundAuthor = authorService.getAuthorById(author.getId());
            if(foundAuthor == null)
                return ResponseEntity.notFound().build();
            authors.add(foundAuthor);
        }
        book.setAuthors(authors);

        //category
        for(Category category : book.getCategories()){
            Category foundCategory = categoryService.getCategoryById(category.getId());
            if(foundCategory == null)
                return ResponseEntity.notFound().build();
            categories.add(foundCategory);
        }
        book.setCategories(categories);

        //publisher
        for(Publisher publisher : book.getPublishers()){
            Publisher foundPublisher = publisherService.getPublisherById(publisher.getId());
            if(foundPublisher == null)
                return ResponseEntity.notFound().build();
            publishers.add(publisher);
        }
        book.setPublishers(publishers);

        Book createdBook = bookService.saveOrUpdateBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

}
