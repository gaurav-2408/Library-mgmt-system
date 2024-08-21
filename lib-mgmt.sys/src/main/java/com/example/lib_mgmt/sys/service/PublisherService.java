package com.example.lib_mgmt.sys.service;

import com.example.lib_mgmt.sys.entity.Publisher;
import com.example.lib_mgmt.sys.repo.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepo publisherRepo;

    public List<Publisher> getAllPublishers(){
        return publisherRepo.findAll();
    }

    public Publisher getPublisherById(int id){
        return publisherRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "No Publisher with this Id"
        ));
    }

    public Publisher saveOrUpdatePublisher (Publisher publisher){
        return publisherRepo.save(publisher);
    }

    public void deletePublisher(int id){
        publisherRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "No Publisher with this Id"
        ));
        publisherRepo.deleteById(id);
    }
}
