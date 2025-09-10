package com.example.demo.service;

import com.example.demo.entity.Follow;
import com.example.demo.entity.User;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public void follow(Long followerId, Long followingId) {
        if (followerId.equals(followingId)) {
            throw new RuntimeException("자기 자신은 팔로우할 수 없습니다.");
        }

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("팔로워 유저 없음"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("팔로잉 유저 없음"));

        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            return; // 이미 팔로우 중
        }

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(follow);
    }

    public List<User> getFollowingList(Long followerId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        return followRepository.findByFollower(follower).stream()
                .map(Follow::getFollowing)
                .collect(Collectors.toList());
    }

    public List<User> getFollowerList(Long followingId) {
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        return followRepository.findByFollowing(following).stream()
                .map(Follow::getFollower)
                .collect(Collectors.toList());
    }
}

