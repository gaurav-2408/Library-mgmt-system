package com.example.lib_mgmt.sys.controller;

import com.example.lib_mgmt.sys.entity.Category;
import com.example.lib_mgmt.sys.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategorys(){
        List<Category> categorys = categoryService.getAllCategorys();
        return ResponseEntity.ok(categorys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id){
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category){
        Category existingCategory =  categoryService.getCategoryById(id);
        if(existingCategory == null)
            ResponseEntity.notFound().build();
        category.setId(id);
        categoryService.saveOrUpdateCategory(category);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        categoryService.saveOrUpdateCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCategory(@PathVariable int id){
        Category existingCategory =  categoryService.getCategoryById(id);
        if(existingCategory == null)
            ResponseEntity.notFound().build();
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
