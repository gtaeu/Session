package com.example.post.service;

import com.example.post.domain.Like;
import com.example.post.domain.Post;
import com.example.post.domain.User;
import com.example.post.repository.LikeRepository;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Long likePost(Long userId, Long postId) {
        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 게시글입니다.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));

        Like like = Like.create(user, post);
        return likeRepository.save(like).getId();
    }
}
