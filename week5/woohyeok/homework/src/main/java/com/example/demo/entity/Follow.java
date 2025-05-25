package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


    @Entity
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Follow {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "follower_id")
        private User follower;

        @ManyToOne
        @JoinColumn(name = "following_id")
        private User following;
    }

