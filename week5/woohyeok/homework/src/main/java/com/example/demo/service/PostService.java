package com.example.demo.service;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 1. 게시글 생성
    public PostResponseDto createPost(PostRequestDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        Post post = Post.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .view(0)
                .time(LocalDateTime.now())
                .updatedAt(null)
                .build();

        Post saved = postRepository.save(post);
        return toResponse(saved);
    }

    // 2. 게시글 상세 조회 + 조회수 증가
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        post = increaseViewCount(post);
        return toResponse(post);
    }

    private Post increaseViewCount(Post post) {
        post = Post.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .view(post.getView() + 1)
                .time(post.getTime())
                .updatedAt(LocalDateTime.now())
                .user(post.getUser())
                .build();

        return postRepository.save(post);
    }

    // 3. 특정 유저의 게시글 전체 조회
    public List<PostResponseDto> getPostsByUser(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return posts.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // 변환 메서드
    private PostResponseDto toResponse(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorName(post.getUser().getNickname())
                .view(post.getView())
                .time(post.getTime())
                .update(post.getUpdatedAt())
                .build();
    }
}


