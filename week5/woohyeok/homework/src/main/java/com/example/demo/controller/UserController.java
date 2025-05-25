package com.example.demo.controller;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v0/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(201).body(userService.createUser(userRequestDto));
    }

    // 사용자 목록 조회
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 사용자 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // 닉네임으로 사용자 조회
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<Optional<User>> getUserByNickname(@PathVariable String nickname) {return ResponseEntity.ok(userService.findByNickname(nickname));}
}
