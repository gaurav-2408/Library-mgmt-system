package com.example.lib_mgmt.sys.service;

import com.example.lib_mgmt.sys.entity.Author;
import com.example.lib_mgmt.sys.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public List<Author> getAllAuthors(){
        return authorRepo.findAll();
    }

    public Author getAuthorById(int id){
        return authorRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "Author not found / Id incorrect"
        ));
    }

    public Author saveOrUpdateAuthor(Author author){
        return authorRepo.save(author);
    }

    public void deleteAuthor(int id){
        authorRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "Author not found / Id incorrect"
        ));
        authorRepo.deleteById(id);
    }
}
