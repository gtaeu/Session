package likelion.shopping_mall.controller;

import likelion.shopping_mall.dto.UserDTO;
import likelion.shopping_mall.dto.request.UserRequest;
import likelion.shopping_mall.dto.response.UserResponse;
import likelion.shopping_mall.entity.User;
import likelion.shopping_mall.repository.UserRepository;
import likelion.shopping_mall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * 회원가입 (비로그인 상태에서 접근 가능)
     */
    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    /**
     * 로그인한 사용자의 내 정보 조회 (JWT 인증 필요)
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyInfo(@AuthenticationPrincipal UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return ResponseEntity.ok(UserResponse.from(user));
    }
}
