package com.example.post.repository;

import com.example.post.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollowerId(Long userId);
    List<Follow> findByFollowingId(Long userId);
}
