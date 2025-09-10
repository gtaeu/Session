package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    List<PostCategory> findByPost(Post post);
}
