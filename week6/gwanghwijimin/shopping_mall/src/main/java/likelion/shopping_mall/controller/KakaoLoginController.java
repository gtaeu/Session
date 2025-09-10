package likelion.shopping_mall.controller;

import likelion.shopping_mall.dto.UserDTO;
import likelion.shopping_mall.entity.Cart;
import likelion.shopping_mall.entity.RefreshToken;
import likelion.shopping_mall.entity.User;
import likelion.shopping_mall.jwt.JWTUtil;
import likelion.shopping_mall.repository.RefreshTokenRepository;
import likelion.shopping_mall.repository.UserRepository;
import likelion.shopping_mall.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class KakaoLoginController {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @PostMapping("/login/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        if (code == null || code.isEmpty()) {
            return ResponseEntity.badRequest().body("Authorization code is missing");
        }

        try {
            // 1. 인가코드로 액세스 토큰 요청
            String accessToken = getAccessToken(code);

            // 2. 카카오 사용자 정보 가져오기
            KakaoUser kakaoUser = getUserInfo(accessToken);
            String kakaoEmail = "kakao_" + kakaoUser.getId();  // 고유 이메일처럼 사용

            // 3. 기존 유저 조회 또는 새 유저 생성
            Optional<User> optionalUser = userRepository.findByEmail(kakaoEmail);
            User user = optionalUser.orElseGet(() -> {
                User newUser = User.builder()
                        .email(kakaoEmail)
                        .name(kakaoUser.getNickname())
                        .password("")
                        .address("")
                        .phone("")
                        .build();
                Cart cart = Cart.builder().user(newUser).build();
                newUser.setCart(cart);
                return userRepository.save(newUser);
            });

            // 4. JWT 발급
            String accessJwt = jwtUtil.createJwt(user.getEmail(), "ROLE_USER", 60 * 60 * 1000L); // 1시간
            String refreshJwt = jwtUtil.createJwt(user.getEmail(), "ROLE_USER", 14 * 24 * 60 * 60 * 1000L); // 2주

            // 5. RefreshToken 저장
            authService.replaceRefreshToken(user.getEmail(), refreshJwt);

            // 6. JSON 응답
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessJwt);
            tokens.put("refreshToken", refreshJwt);
            return ResponseEntity.ok(tokens);

        } catch (IllegalArgumentException e) {
            // 인가코드가 잘못됐거나 만료됨 → 400
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // 기타 서버 문제 → 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserDTO userDTO) {
        String email = userDTO.getUsername();
        authService.deleteRefreshToken(email);  // ✅ RefreshToken 삭제
        return ResponseEntity.ok("Logged out successfully");
    }

    private String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "https://kauth.kakao.com/oauth/token",
                    request,
                    Map.class
            );

            Map<String, Object> body = response.getBody();
            if (body == null || !body.containsKey("access_token")) {
                throw new IllegalArgumentException("응답에 access_token이 없습니다.");
            }

            return (String) body.get("access_token");

        } catch (org.springframework.web.client.HttpClientErrorException e) {
            // 잘못된 인가코드 (재사용 등) → 400으로 처리되도록 예외 던짐
            throw new IllegalArgumentException("잘못된 인가코드입니다. 이미 사용됐거나 만료되었습니다.");
        } catch (Exception e) {
            // 서버 내부 오류는 그대로 예외로 전달
            throw new RuntimeException("카카오 토큰 요청 중 오류: " + e.getMessage());
        }
    }


    private KakaoUser getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                Map.class
        );

        Map<String, Object> body = response.getBody();
        Long id = ((Number) body.get("id")).longValue();

        Map<String, Object> kakaoAccount = (Map<String, Object>) body.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String nickname = (String) profile.get("nickname");

        return new KakaoUser(id, nickname);
    }

    static class KakaoUser {
        private final Long id;
        private final String nickname;

        public KakaoUser(Long id, String nickname) {
            this.id = id;
            this.nickname = nickname;
        }

        public Long getId() { return id; }
        public String getNickname() { return nickname; }
    }
}
