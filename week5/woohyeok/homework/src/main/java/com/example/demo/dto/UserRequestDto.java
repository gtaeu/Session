package com.example.demo.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto{

    private String nickname;
    private String password;
    private String email;
}
