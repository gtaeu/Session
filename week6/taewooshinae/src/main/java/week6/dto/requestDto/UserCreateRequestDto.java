package week6.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequestDto {
    private String email;
    private String password;
    private String nickname;
}
