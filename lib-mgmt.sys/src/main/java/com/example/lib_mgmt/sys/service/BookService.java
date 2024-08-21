package com.example.lib_mgmt.sys.service;

import com.example.lib_mgmt.sys.entity.Book;
import com.example.lib_mgmt.sys.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book>getAllBooks(){
        return bookRepo.findAll();
    }

    public Book getBookById(int id){
        return bookRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "No Book with this Id"
        ));
    }

    public Book saveOrUpdateBook (Book book){
        return bookRepo.save(book);
    }

    public void deleteBook(int id){
        bookRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "No Book with this Id"
        ));
        bookRepo.deleteById(id);
    }
}
