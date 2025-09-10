package mutsa.session4.dto;

import lombok.Builder;
import lombok.Getter;
import mutsa.session4.entity.User;

@Getter
@Builder
public class UserResponseDto {

    private Long userId;
    private String username;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
            .userId(user.getId())
            .username(user.getUsername())
            .build();
    }
}
