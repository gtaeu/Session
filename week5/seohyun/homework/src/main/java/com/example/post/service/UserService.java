package com.example.post.service;

import com.example.post.domain.User;
import com.example.post.dto.UserRequestDto;
import com.example.post.dto.UserResponseDto;
import com.example.post.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserRequestDto dto) {
        userRepository.findByEmail(dto.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        });

        User user = User.create(dto.getEmail(), dto.getNickname(), dto.getPassword());
        return userRepository.save(user).getId();
    }

    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다."));
        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt());
    }

}
