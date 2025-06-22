package com.example.post.service;

import com.example.post.domain.Follow;
import com.example.post.domain.User;
import com.example.post.repository.FollowRepository;
import com.example.post.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public Long follow(Long followerId, Long followingId) {
        if (followerId.equals(followingId)) {
            throw new IllegalArgumentException("자기 자신은 팔로우할 수 없습니다.");
        }

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new EntityNotFoundException("팔로워 없음"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new EntityNotFoundException("팔로잉 대상 없음"));

        Follow follow = Follow.create(follower, following);
        return followRepository.save(follow).getId();
    }

    public List<String> getFollowingList(Long userId) {
        return followRepository.findByFollowerId(userId).stream()
                .map(f -> f.getFollowing().getNickname())
                .collect(Collectors.toList());
    }

    public List<String> getFollowerList(Long userId) {
        return followRepository.findByFollowingId(userId).stream()
                .map(f -> f.getFollower().getNickname())
                .collect(Collectors.toList());
    }
}
