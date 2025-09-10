package week6.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import week6.entity.User;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String email;
    private String nickname;

    public UserResponseDto(User user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
