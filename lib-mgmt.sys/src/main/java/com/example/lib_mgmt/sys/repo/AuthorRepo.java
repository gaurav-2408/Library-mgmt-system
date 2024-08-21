package com.example.lib_mgmt.sys.repo;

import com.example.lib_mgmt.sys.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
