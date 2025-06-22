package com.example.post.dto;

import lombok.Getter;

@Getter
public class PostCreateRequestDto {
    private Long userId;
    private String title;
    private String content;
}
