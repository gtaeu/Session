package com.example.post.repository;

import com.example.post.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    boolean existsByCategory(String category);
}
