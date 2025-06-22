package com.example.post.controller;

import com.example.post.dto.LikeRequestDto;
import com.example.post.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Long> likePost(@RequestBody LikeRequestDto dto) {
        return ResponseEntity.ok(likeService.likePost(dto.getUserId(), dto.getPostId()));
    }

}
