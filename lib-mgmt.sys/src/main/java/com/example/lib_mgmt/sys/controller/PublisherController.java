package com.example.lib_mgmt.sys.controller;

import com.example.lib_mgmt.sys.entity.Publisher;
import com.example.lib_mgmt.sys.repo.PublisherRepo;
import com.example.lib_mgmt.sys.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers(){
        List<Publisher> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisher(@PathVariable int id){
        Publisher publisher = publisherService.getPublisherById(id);
        if(publisher == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable int id, @RequestBody Publisher publisher){
        Publisher existingPublisher = publisherService.getPublisherById(id);
        if (existingPublisher == null)
            ResponseEntity.notFound().build();
        publisher.setId(id);
        publisherService.saveOrUpdatePublisher(publisher);
        return ResponseEntity.ok(publisher);
    }

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher){
        publisherService.saveOrUpdatePublisher(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id){
        Publisher existingPublisher = publisherService.getPublisherById(id);
        if (existingPublisher == null)
            ResponseEntity.notFound().build();
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
