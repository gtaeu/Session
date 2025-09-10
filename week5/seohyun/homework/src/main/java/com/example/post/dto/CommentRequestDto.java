package com.example.post.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long userId;
    private Long postId;
    private String content;
}
