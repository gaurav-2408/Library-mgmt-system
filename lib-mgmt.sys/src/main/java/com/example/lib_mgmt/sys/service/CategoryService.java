package com.example.lib_mgmt.sys.service;

import com.example.lib_mgmt.sys.entity.Category;
import com.example.lib_mgmt.sys.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategorys(){
        return categoryRepo.findAll();
    }

    public Category getCategoryById(int id){
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "No Category with this Id"
        ));
    }

    public Category saveOrUpdateCategory (Category category){
        return categoryRepo.save(category);
    }

    public void deleteCategory(int id){
        categoryRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "No Category with this Id"
        ));
        categoryRepo.deleteById(id);
    }
}
