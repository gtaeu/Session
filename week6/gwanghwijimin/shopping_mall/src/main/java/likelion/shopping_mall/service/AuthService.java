package likelion.shopping_mall.service;

import jakarta.transaction.Transactional;
import likelion.shopping_mall.entity.RefreshToken;
import likelion.shopping_mall.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void replaceRefreshToken(String email, String newToken) {
        refreshTokenRepository.deleteByEmail(email);
        refreshTokenRepository.flush(); // 중복 에러 방지
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .email(email)
                        .token(newToken)
                        .build()
        );
    }

    @Transactional
    public void deleteRefreshToken(String email) {
        refreshTokenRepository.deleteByEmail(email);
    }
}
