package com.example.demo.controller;

import com.example.demo.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post-categories")
public class PostCategoryController {

    private final PostCategoryService postCategoryService;

    @PostMapping("/{postId}/{categoryId}")
    public ResponseEntity<Void> addCategoryToPost(@PathVariable Long postId, @PathVariable Long categoryId) {
        postCategoryService.addCategoryToPost(postId, categoryId);
        return ResponseEntity.ok().build();
    }
}

