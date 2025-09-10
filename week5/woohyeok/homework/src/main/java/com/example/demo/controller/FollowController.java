package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followerId}/{followingId}")
    public ResponseEntity<Void> follow(@PathVariable Long followerId, @PathVariable Long followingId) {
        followService.follow(followerId, followingId);
        return ResponseEntity.ok().build();
    }

    // [1] 내가 팔로우한 사람들 (팔로잉 리스트)
    @GetMapping("/following/{followerId}")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Long followerId) {
        return ResponseEntity.ok(followService.getFollowingList(followerId));
    }

    // [2] 나를 팔로우한 사람들 (팔로워 리스트)
    @GetMapping("/followers/{followingId}")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Long followingId) {
        return ResponseEntity.ok(followService.getFollowerList(followingId));
    }
}

