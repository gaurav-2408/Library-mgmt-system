package com.example.lib_mgmt.sys.repo;

import com.example.lib_mgmt.sys.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
