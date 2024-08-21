package com.example.lib_mgmt.sys.repo;

import com.example.lib_mgmt.sys.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
}
