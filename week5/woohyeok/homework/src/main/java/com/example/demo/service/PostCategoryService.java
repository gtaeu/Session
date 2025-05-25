package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.PostCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PostCategoryRepository;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCategoryService {

    private final PostCategoryRepository postCategoryRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public void addCategoryToPost(Long postId, Long categoryId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));

        PostCategory postCategory = PostCategory.builder()
                .post(post)
                .category(category)
                .build();

        postCategoryRepository.save(postCategory);
    }
}

