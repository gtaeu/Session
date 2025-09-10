package com.example.post.service;

import com.example.post.domain.Post;
import com.example.post.domain.User;
import com.example.post.dto.PostCreateRequestDto;
import com.example.post.dto.PostDetailDto;
import com.example.post.dto.PostListDto;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long createPost(PostCreateRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

        Post post = Post.create(user, dto.getTitle(), dto.getContent());
        return postRepository.save(post).getId();
    }

    public PostDetailDto getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다."));

        return new PostDetailDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getViewCount(),
                post.getUser().getEmail(),
                post.getCreatedAt(),
                post.getModifiedAt()
        );
    }

    public List<PostListDto> getPostsByUserId(Long userId) {
        return postRepository.findAll().stream()
                .filter(post -> post.getUser().getId().equals(userId))
                .map(post -> new PostListDto(
                        post.getId(),
                        post.getTitle(),
                        post.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }


}
