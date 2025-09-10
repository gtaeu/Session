package mutsa.session4.controller;

import lombok.RequiredArgsConstructor;
import mutsa.session4.dto.UserRequestDto;
import mutsa.session4.dto.UserResponseDto;
import mutsa.session4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/users")
    public ResponseEntity<Long> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(201).body(userService.createUser(userRequestDto));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> readUser(@PathVariable Long userId) {
        return ResponseEntity.status(200).body(userService.findById(userId));
    }
}
