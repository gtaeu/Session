package com.example.post.service;

import com.example.post.domain.Comment;
import com.example.post.domain.Post;
import com.example.post.domain.User;
import com.example.post.dto.CommentRequestDto;
import com.example.post.dto.CommentResponseDto;
import com.example.post.repository.CommentRepository;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Long createComment(CommentRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저 없음"));
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));

        Comment comment = Comment.create(user, post, dto.getContent());
        return commentRepository.save(comment).getId();
    }

    public List<CommentResponseDto> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(c -> new CommentResponseDto(c.getId(), c.getContent(), c.getUser().getNickname(), c.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
