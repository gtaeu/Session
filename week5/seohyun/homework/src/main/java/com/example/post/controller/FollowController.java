package com.example.post.controller;

import com.example.post.dto.FollowRequestDto;
import com.example.post.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping
    public ResponseEntity<Long> follow(@RequestBody FollowRequestDto dto) {
        return ResponseEntity.ok(followService.follow(dto.getFollowerId(), dto.getFollowingId()));
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<String>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowingList(userId));
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<String>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowerList(userId));
    }
}
