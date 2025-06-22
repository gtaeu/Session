package com.example.post.dto;

import lombok.Getter;

@Getter
public class FollowRequestDto {
    private Long followerId;
    private Long followingId;
}
