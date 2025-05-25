package com.example.demo.controller;

import com.example.demo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<Void> like(@PathVariable Long userId, @PathVariable Long postId) {
        likeService.like(userId, postId);
        return ResponseEntity.ok().build();
    }
}

