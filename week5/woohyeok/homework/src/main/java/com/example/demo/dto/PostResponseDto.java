package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private int view;
    private LocalDateTime time;
    private LocalDateTime update;
}
