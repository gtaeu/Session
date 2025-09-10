package com.example.demo.repository;

import com.example.demo.entity.Follow;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFollowerAndFollowing(User follower, User following);

    // 내가 팔로우한 유저들
    List<Follow> findByFollower(User follower);

    // 나를 팔로우한 유저들
    List<Follow> findByFollowing(User following);
}

