package com.example.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private String authorEmail;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
