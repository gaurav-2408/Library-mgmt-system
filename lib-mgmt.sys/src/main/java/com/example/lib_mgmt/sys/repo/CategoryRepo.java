package com.example.lib_mgmt.sys.repo;

import com.example.lib_mgmt.sys.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
