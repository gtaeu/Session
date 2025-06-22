package com.example.post.service;

import com.example.post.domain.Category;
import com.example.post.dto.CategoryRequestDto;
import com.example.post.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long createCategory(CategoryRequestDto dto) {
        if (categoryRepository.existsByCategory(dto.getCategory())) {
            throw new IllegalArgumentException("중복된 카테고리입니다.");
        }

        Category category = Category.create(dto.getCategory());
        return categoryRepository.save(category).getId();
    }
}
